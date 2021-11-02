package com.yanolja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanolja.dao.UserDAO;
import com.yanolja.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	
	@Override
	public boolean registerUser(UserDTO params){
		int queryResult = 0;
		
		if(params.getUserId() == null) {
			queryResult = userDAO.insertUser(params);
		}else {
		}
		return (queryResult == 1) ? true : false;
	}
}
