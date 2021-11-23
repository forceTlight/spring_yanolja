package com.yanolja.controller;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.User;
import com.yanolja.repository.user.UserRepository;
import com.yanolja.service.UserService;
import com.yanolja.utils.JwtAuthenticationProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JwtAuthenticationProvider jwtAuthenticationProvider;
	/*
		@param User.RegisterReq
	 	회원가입
	 */
	@PostMapping(value="/register")
	@ApiOperation(value = "유저등록", notes = "유저를 새로 등록함.")
	public DefaultResponse<User.RegisterRes> userCreate(@RequestBody User.RegisterReq userReq){
		try {
			User.RegisterRes userRes = userService.register(userReq);
			return new DefaultResponse<User.RegisterRes>(StatusCode.CREATED,ResponseMessage.CREATED_USER, userRes);
		}catch(DefaultException e) { // 암호화 에러
			return new DefaultResponse<>(e.getStatusCode(),e.getMessage());
		}catch (Exception e){ // 데이터베이스 에러
			return new DefaultResponse<>(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
		}
	}
	/*
		@param User.LoginReq
	 	로그인
	 */
	@PostMapping(value="/login")
	@ApiOperation(value = "로그인", notes = "유저를 새로 등록함.")
	public DefaultResponse<User.LoginRes> userLogin(@RequestBody User.LoginReq userReq,
													HttpServletResponse response){
		try {
			User.LoginRes loginRes = userService.login(userReq);
			// JWT TOKEN 설정
			String token = jwtAuthenticationProvider.createToken(loginRes.getEmail());
			response.setHeader("X-AUTH-TOKEN", token);
			return new DefaultResponse<User.LoginRes>(StatusCode.LOGIN_SUCCESS, ResponseMessage.LOGIN_SUCCESS,loginRes);
		}catch(DefaultException e) { // 복호화 에러, 로그인 실패
			return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
		}
	}
	@GetMapping(value="")
	@ApiOperation(value = "유저 조회", notes = "닉네임으로 유저를 조회함, 닉네임을 안적으면 전체 유저 리스트를 반환한다.")
	public DefaultResponse<User.Info> userFindById(@RequestParam(required = false) String nickName){
		try{
			User.Info user = userService.findByNickName(nickName);
			return new DefaultResponse<User.Info>(StatusCode.SEARCH_SUCCESS, ResponseMessage.SEARCH_USER, user);
		/*}catch(DefaultException e){
			return new DefaultResponse<>(e.getStatusCode(), e.getMessage());*/
		}catch (Exception e){
			return new DefaultResponse<>(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
		}
	}
	/*
		@required jwtToken
		@param User.NameReq
		유저 닉네임 수정
	 */
	@PatchMapping(value="/{userId}")
	@ApiOperation(value = "유저 닉네임 수정", notes = "유저 닉네임을 수정한다.")
	public DefaultResponse<String> userUpdate(@PathVariable("userId") int userId, @RequestBody User.NameReq nameReq
	, HttpServletRequest request){
		String nickName = nameReq.getNickName();
		User.PatchReq userReq = User.PatchReq.builder().userId(userId).nickName(nickName).build();
		try {
			userService.updateNickName(userReq);
			// userId로 email 가져오기
			String userEmail = userRepository.getEmailById(userId);
			// jwt에서 email 추출
			String jwtEmail = jwtAuthenticationProvider.getUserPk(request);
			// jwt validation
			if(!jwtEmail.equals(userEmail)){
				throw new DefaultException(ResponseMessage.INVALID_JWT, StatusCode.JWT_ERROR);
			}
			// 같다면 유저 네임 변경
			return new DefaultResponse<String>(StatusCode.OK, ResponseMessage.UPDATE_USER);
		}catch(DefaultException e) {
			log.error(e.toString());
			return new DefaultResponse<String>(e.getStatusCode(), e.getMessage());
		}
	}
	@PatchMapping(value="/delete/{userId}")
	@ApiOperation(value = "유저삭제", notes = "userId를 받아서 유저를 삭제한다.")
	public DefaultResponse<String> userDelete(@PathVariable("userId") int userId){
		try {
			userService.deleteById(userId);
			return new DefaultResponse<String>(StatusCode.OK, ResponseMessage.DELETE_USER);
		}catch(DefaultException e) {
			log.error(e.toString());
			return new DefaultResponse<String>(e.getStatusCode(), e.getMessage());
		}
	}
}
