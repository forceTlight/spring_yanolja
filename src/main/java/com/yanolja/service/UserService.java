package com.yanolja.service;

import com.yanolja.configuration.DefaultException;
import com.yanolja.domain.UserDTO;

public interface UserService {
	public UserDTO.RegisterRes register(UserDTO.RegisterReq userReq) throws Exception;
	public UserDTO.LoginRes login(UserDTO.LoginReq userReq) throws Exception;
	public Integer updateNickName(UserDTO.PatchReq user) throws DefaultException;
	public Integer deleteById(Integer id) throws DefaultException;
}
