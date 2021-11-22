package com.yanolja.service;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.Status;
import com.yanolja.domain.User;
import com.yanolja.repository.user.UserRepository;
import com.yanolja.utils.AES128;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService{
	private String user_secret_key = "1234567890asdfgh";
	@Autowired
	private UserRepository userRepository;
	/*
		회원가입
	 */
	public User.RegisterRes register(User.RegisterReq user) throws DefaultException {
		//!! 예외처리
		// 이미 존재하는 이메일에 회원가입 하려할 때
		String email = user.getEmail();
		User.Info realUser = userRepository.findByEmail(email);
		if(realUser != null){
			throw new DefaultException(ResponseMessage.EXIST_USER, Status.REGISTER_FAIL);
		}
		// 암호화(AES128) key : user_secret_key
		try { // 암호화 예외처리
			AES128 aes128 = new AES128(user_secret_key);
			String pwd = aes128.encrypt(user.getPassword());
			user.setPassword(pwd);
		}catch(Exception encryptError){
			throw new DefaultException(ResponseMessage.ENCRYPT_ERROR, Status.ENCRYPT_ERROR);
		}
		return userRepository.insert(user);
	}
	/*
		로그인 - 예외처리 : 1. 아이디 존재 X, 2. 비밀번호가 틀릴 때 3. 복호화 하는도중 에러날 때
		JwtToken을 받아서 로그인을 진행함
	 */
	public User.LoginRes login(User.LoginReq user) throws DefaultException {
		String email = user.getEmail();
		User.Info realUser;
		// 유저 아이디로 유저 찾기
		try{
			realUser = userRepository.findByEmail(email);
		}catch (Exception e){
			throw new DefaultException(ResponseMessage.LOGIN_FAIL, Status.LOGIN_FAIL);
		}
		// !! 예외처리
		// 아이디가 존재하지 않을 때
		if(realUser == null){
			throw new DefaultException(ResponseMessage.NOT_FOUND_USER, Status.LOGIN_FAIL);
		}
		// 복호화(AES128) <key : neo>
		String pwd;
		try { // 복호화 예외처리
			AES128 aes128 = new AES128(user_secret_key);
			pwd = aes128.decrypt(realUser.getPassword());
		}catch(Exception decryptError){
			throw new DefaultException(ResponseMessage.DECRYPT_ERROR, Status.DECRYPT_ERROR);
		}
		// 비밀번호 맞는지 비교
		if (user.getPassword().equals(pwd)) {
			return User.LoginRes.builder().userId(realUser.getUserId()).email(realUser.getEmail()).build();
		} else {
			// !! 예외처리
			// 비밀번호가 틀릴 때
			throw new DefaultException(ResponseMessage.NOT_MATCHING_PASSWORD, Status.LOGIN_FAIL);
		}
	}
	public Integer updateNickName(User.PatchReq user) throws DefaultException{
		log.debug("user Id = {}", user.getUserId());
		int result = userRepository.updateById(user);
		if(result == 0){ // 0이면 에러가 발생
			throw new DefaultException(ResponseMessage.DB_ERROR, Status.DB_ERROR);
		}else{
			return result;
		}
	}
	public Integer deleteById(Integer id) throws DefaultException{
		log.debug("user id = {}", id);
		int result = userRepository.deleteById(id);
		if(result == 0){
			throw new DefaultException(ResponseMessage.DB_ERROR, Status.DB_ERROR);
		}else{
			return result;
		}
	}
}
