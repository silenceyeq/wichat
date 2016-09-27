package com.wichat.service;

import java.util.Map;

import com.wichat.entity.UserToken;

/**
 *  Service
 * 
 * @author 2016-09-27 04:16:01
 */
public interface UserTokenService{
	
	public UserToken getById(Integer id);
	
	public void insert(UserToken tokens);
	
	public int updateByPrimaryKeySelective(UserToken token);
	
	public UserToken queryByCondition(Map<String, Object> params);
}
