package com.wichat.service;

import java.util.Map;

import com.wichat.entity.User;

/**
 * Users and global privileges Service
 * 
 * @author 2016-09-27 11:18:08
 */
public interface UserService{

	public User getById(Integer id);
	
	public User queryByCondition(Map<String, Object> params);
}
