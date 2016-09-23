package com.wichat.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wichat.mybatis.DomainObject;

public class ServiceSupportImpl<T extends DomainObject> implements ServiceSupport<T> {

	@Override
	public T get(Object identity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable insert(T domainObj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(T domainObj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recycle(String identity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object identity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(Object identity) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T get(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<T> domainObjectList) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdate(List<T> domainObjectList) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long count(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<T> getByIdList(List<Object> idList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getByBesideIdList(List<Object> besideIdList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
