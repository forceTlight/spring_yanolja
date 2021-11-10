package com.yanolja.repository.user;

import com.yanolja.domain.UserDTO;

public interface UserRepository {
	public UserDTO insert(UserDTO user);
	public Integer updateById(UserDTO user);
	public Integer deleteById(Integer id);
	public UserDTO findById(Integer id);
	public UserDTO findByName(String name);
}
