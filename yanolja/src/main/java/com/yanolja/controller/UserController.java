package com.yanolja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yanolja.dto.UserDTO;
import com.yanolja.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/user/register")
	public String registerUser(@RequestBody UserDTO user) {
		boolean isRegistered = userService.registerUser(user);
		if(isRegistered == false) {
			return "게시글 등록에 실패하였습니다.";
		}
		return "게시글 등록에 성공하였습니다.";
	}
}
