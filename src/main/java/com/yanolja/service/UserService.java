package com.yanolja.service;

import com.yanolja.domain.UserDTO;

public interface UserService {
	public UserDTO register(UserDTO user) throws Exception;
	public UserDTO login(UserDTO user) throws Exception;
	public Integer updateById(UserDTO user);
	public Integer deleteById(Integer id);
	public UserDTO findById(Integer id);
}
