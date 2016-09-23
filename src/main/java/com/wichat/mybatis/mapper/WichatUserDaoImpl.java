package com.wichat.mybatis.mapper;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wichat.mybatis.po.WichatUser;

/**
 * 用户表 Dao实现类
 * 
 * @author 2016-09-23 05:37:58 zrr
 */
public class WichatUserDaoImpl extends HibernateDaoSupportImpl<WichatUser>
		implements WichatUserDao {

	@Override
	public WichatUser get(Object identity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable insert(WichatUser domainObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(WichatUser domainObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recycle(Object identity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object identity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(WichatUser domainObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExist(Object identity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchInsert(List<WichatUser> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<WichatUser> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WichatUser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WichatUser> getList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WichatUser> getByIdList(List<Object> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WichatUser> getByBesideIdList(List<Object> besideIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WichatUser get(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}


}
