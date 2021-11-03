package com.yanolja.service;

import com.yanolja.domain.UserDTO;

public interface UserServiceInterface {
	public UserDTO insert(UserDTO user);
	public Integer updateById(UserDTO user);
	public Integer deleteById(Integer id);
}
