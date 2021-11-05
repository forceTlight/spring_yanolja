package com.yanolja.controller;

import com.yanolja.domain.UserDTO;
import com.yanolja.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping(value="/register")
	@ApiOperation(value = "유저등록", notes = "유저를 새로 등록함.")
	public ResponseEntity<UserDTO> userCreate(@RequestBody UserDTO user){
		try {
			log.debug("user = {}", user.toString());
			return new ResponseEntity<>(userService.insert(user), HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value="/find/{userId}")
	@ApiOperation(value = "유저상세조회", notes = "userId로 유저를 조회함.")
	public ResponseEntity<UserDTO> userFindById(@PathVariable Integer userId){
		try{
			log.debug("user = {}", userId);
			UserDTO user = userService.findById(userId);
			return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
		}catch(Exception e){
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(value="/update")
	@ApiOperation(value = "유저수정", notes = "유저 레이블을 수정한다.")
	public ResponseEntity<String> userUpdate(@RequestBody UserDTO user){
		try {
			log.debug("user = {}", user.toString());
			Integer updatedCnt = userService.updateById(user);
			return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping(value="/delete/{userId}")
	@ApiOperation(value = "유저삭제", notes = "userId를 받아서 유저를 삭제한다.")
	public ResponseEntity<String> userDelete(@PathVariable Integer userId){
		try {
			log.debug("user id = {}", userId);
			Integer deletedCnt = userService.deleteById(userId);
			return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
