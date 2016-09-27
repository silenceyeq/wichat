package com.wichat.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wichat.entity.User;
import com.wichat.mybatis.mapper.UserMapper;
import com.wichat.service.UserService;

/**
 * Users and global privileges Service实现类
 * 
 * @author 2016-09-27 11:18:08
 */
@Service
public class UserServiceImpl implements
		UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User getById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User queryByCondition(Map<String, Object> params) {
		return userMapper.queryByCondition(params );
	}


}
