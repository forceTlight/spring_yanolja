package com.yanolja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yanolja.domain.UserDTO;
import com.yanolja.repository.UserRepository;
import com.yanolja.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/user/register")
	public ResponseEntity<UserDTO> userCreate(@RequestBody UserDTO user){
		try {
			log.debug("user = {}", user.toString());
			return new ResponseEntity<>(userService.insert(user), HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value="/user/update")
	public ResponseEntity<String> userUpdate(@RequestBody UserDTO user){
		try {
			log.debug("user = {}", user.toString());
			Integer updatedCnt = userService.updateById(user);
			return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value="/user/delete")
	public ResponseEntity<String> userDelete(@RequestParam(value="userId") Integer id){
		try {
			log.debug("user id = {}", id);
			Integer deletedCnt = userService.deleteById(id);
			return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
