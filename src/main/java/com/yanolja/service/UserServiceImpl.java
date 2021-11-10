package com.yanolja.service;

import com.yanolja.domain.UserDTO;
import com.yanolja.repository.user.UserRepositoryImpl;
import com.yanolja.utils.AES128;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepositoryImpl userRepository;
	public UserDTO register(UserDTO user) throws Exception {
		//TODO 아이디 유효성 검사
		/*

		 */
		//TODO 비밀번호 10자 이상인지 유효성 검사
		/*

		 */
		// 암호화(AES128) key : neo
		AES128 aes128 = new AES128("neo");
		String pwd = aes128.encrypt(user.getPassword());
		user.setPassword(pwd);
		return userRepository.insert(user);
	}
	public UserDTO login(UserDTO user) throws Exception {
		String name = user.getName();
		// 유저 아이디로 유저 찾기
		UserDTO realUser = userRepository.findByName(name);
		// 복호화(AES128) key : neo
		AES128 aes128 = new AES128("neo");
		String pwd = aes128.decrypt(user.getPassword());
		// 비밀번호 맞는지 비교
		if(realUser.getPassword().equals(pwd)){
			return realUser;
		}else{
			// TODO 예외 처리
			/*

			 */
			return null;
		}
	}
	public Integer updateById(UserDTO user) {
		log.debug("user Id = {}", user.getUserId());
		return userRepository.updateById(user);
	}
	public Integer deleteById(Integer id) {
		log.debug("user id = {}", id);
		return userRepository.deleteById(id);
	}
	public UserDTO findById(Integer id){
		log.debug("user Id = {}", id);
		return userRepository.findById(id);
	}
}
