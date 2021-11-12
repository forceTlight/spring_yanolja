package com.yanolja.service;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.UserDTO;
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
	public UserDTO.RegisterRes register(UserDTO.RegisterReq user) throws DefaultException {
		//TODO 아이디 유효성 검사
		/*

		 */

		// 암호화(AES128) key : user_secret_key
		try { // 암호화 예외처리
			AES128 aes128 = new AES128(user_secret_key);
			String pwd = aes128.encrypt(user.getPassword());
			user.setPassword(pwd);
		}catch(Exception encryptError){
			throw new DefaultException(ResponseMessage.ENCRYPT_ERROR, StatusCode.ENCRYPT_ERROR);
		}
		return userRepository.insert(user);
	}
	public UserDTO.LoginRes login(UserDTO.LoginReq user) throws DefaultException {
		String email = user.getEmail();
		// 유저 아이디로 유저 찾기
		UserDTO.Info realUser = userRepository.findByEmail(email);
		// TODO 아이디 존재하는지 예외 처리
		/*

		 */
		// 복호화(AES128) key : neo
		String pwd;
		try { // 복호화 예외처리
			AES128 aes128 = new AES128(user_secret_key);
			pwd = aes128.decrypt(realUser.getPassword());
		}catch(Exception decryptError){
			throw new DefaultException(ResponseMessage.DECRYPT_ERROR, StatusCode.DECRYPT_ERROR);
		}
		// 비밀번호 맞는지 비교
		if (user.getPassword().equals(pwd)) {
			return UserDTO.LoginRes.builder().userId(realUser.getUserId()).build();
		} else {
			throw new DefaultException(ResponseMessage.LOGIN_FAIL, StatusCode.LOGIN_FAIL);
		}
	}
	public Integer updateNickName(UserDTO.PatchReq user) throws DefaultException{
		log.debug("user Id = {}", user.getUserId());
		int result = userRepository.updateById(user);
		if(result == 0){ // 0이면 에러가 발생
			throw new DefaultException(ResponseMessage.DB_ERROR, StatusCode.DB_ERROR);
		}else{
			return result;
		}
	}
	public Integer deleteById(Integer id) throws DefaultException{
		log.debug("user id = {}", id);
		int result = userRepository.deleteById(id);
		if(result == 0){
			throw new DefaultException(ResponseMessage.DB_ERROR, StatusCode.DB_ERROR);
		}else{
			return result;
		}
	}
}
