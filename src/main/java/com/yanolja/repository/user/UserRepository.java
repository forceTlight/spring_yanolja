package com.yanolja.repository.user;

import com.yanolja.domain.UserDTO;

public interface UserRepository {
	public UserDTO.RegisterRes insert(UserDTO.RegisterReq user);
	public Integer updateById(UserDTO.PatchReq user);
	public Integer deleteById(Integer id);
	public UserDTO.Info findById(Integer id);
	public UserDTO.Info findByEmail(String email);
}
