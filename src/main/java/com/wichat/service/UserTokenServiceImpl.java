package com.wichat.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wichat.entity.UserToken;
import com.wichat.mybatis.mapper.UserTokenMapper;

/**
 *  Service实现类
 * 
 * @author 2016-09-27 04:16:01
 */
@Service
public class UserTokenServiceImpl implements
		UserTokenService {
	@Autowired
	private UserTokenMapper userTokenMapper;

	@Override
	public void insert(UserToken token) {
		userTokenMapper.insertSelective(token);
	}

	@Override
	public UserToken getById(Integer id) {
		return userTokenMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserToken token) {
		return userTokenMapper.updateByPrimaryKeySelective(token);
	}

	@Override
	public UserToken queryByCondition(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userTokenMapper.queryByCondition(params);
	}


}
