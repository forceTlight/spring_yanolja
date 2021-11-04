package com.yanolja.service;

import com.yanolja.domain.UserDTO;
import com.yanolja.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserServiceInterface{
	@Autowired
	private UserRepository userRepository;

	public UserDTO insert(UserDTO user) {
		return userRepository.insert(user);
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
