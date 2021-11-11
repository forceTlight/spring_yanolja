package com.yanolja.controller;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.DefaultRes;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.UserDTO;
import com.yanolja.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceImpl userService;

	// 회원가입
	@PostMapping(value="/register")
	@ApiOperation(value = "유저등록", notes = "유저를 새로 등록함.")
	public DefaultRes<UserDTO.RegisterRes> userCreate(@RequestBody UserDTO.RegisterReq userReq){
		try {
			log.debug("user = {}", userReq.toString());
			UserDTO.RegisterRes userRes = userService.register(userReq);
			return new DefaultRes<UserDTO.RegisterRes>(StatusCode.CREATED,ResponseMessage.CREATED_USER, userRes);
		}catch(DefaultException e) { // 암호화 에러
			log.error(e.toString());
			return new DefaultRes<>(e.getStatusCode(),e.getMessage());
		}
	}
	// 로그인
	@PostMapping(value="/login")
	@ApiOperation(value = "로그인", notes = "유저를 새로 등록함.")
	public DefaultRes<UserDTO.LoginRes> userLogin(@RequestBody UserDTO.LoginReq userReq){
		try {
			log.debug("user = {}", userReq.toString());
			return new DefaultRes<UserDTO.LoginRes>(StatusCode.LOGIN_SUCCESS, ResponseMessage.LOGIN_SUCCESS,userService.login(userReq));
		}catch(DefaultException e) { // 복호화 에러, 로그인 실패
			log.error(e.toString());
			return new DefaultRes<>(e.getStatusCode(), e.getMessage());
		}
	}/*
	@GetMapping(value="")
	@ApiOperation(value = "유저 조회", notes = "닉네임으로 유저를 조회함, 닉네임을 안적으면 전체 유저 리스트를 반환한다.")
	public DefaultRes<UserDTO.Info> userFindById(@PathVariable(required = false) String name){
		try{
			log.debug("user = {}", userId);
			UserDTO user = userService.findById(userId);
			return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
		}catch(Exception e){
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	@ResponseBody
	@PatchMapping(value="/{userId}")
	@ApiOperation(value = "유저 닉네임 수정", notes = "유저 닉네임을 수정한다.")
	public DefaultRes<String> userUpdate(@PathVariable("userId") int userId, @RequestBody UserDTO.NameReq user){
		UserDTO.PatchReq userReq = UserDTO.PatchReq.builder().userId(userId).name(user.getName()).build();
		try {
			userService.updateNickName(userReq);
			return new DefaultRes<String>(StatusCode.OK, ResponseMessage.UPDATE_USER);
		}catch(DefaultException e) {
			log.error(e.toString());
			return new DefaultRes<String>(e.getStatusCode(), e.getMessage());
		}
	}
	@GetMapping(value="/delete/{userId}")
	@ApiOperation(value = "유저삭제", notes = "userId를 받아서 유저를 삭제한다.")
	public DefaultRes<String> userDelete(@PathVariable("userId") int userId){
		try {
			log.debug("user id = {}", userId);
			userService.deleteById(userId);
			return new DefaultRes<String>(StatusCode.OK, ResponseMessage.DELETE_USER);
		}catch(DefaultException e) {
			log.error(e.toString());
			return new DefaultRes<String>(e.getStatusCode(), e.getMessage());
		}
	}
}
