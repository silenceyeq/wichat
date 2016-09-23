package com.wichat.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wichat.mybatis.DomainObject;


public interface ServiceSupport<T extends DomainObject> {

	/**获取领域对象信息
	 * @param identity 领域信息标识
	 * @return
	 * @throws ServiceException 
	 */
	T get(Object identity) throws Exception;
	
	/**添加领域对象信息
	 * @param domainObj 领域对象
	 * @throws ServiceException 
	 */
	Serializable insert(T domainObj) throws Exception;
	
	/**修改领域对象信息
	 * @param domainObj 领域对象
	 */
	void update(T domainObj) throws Exception;
	
	
	/**逻辑删除分销商信息
	 * @param identity 领域信息标识
	 */
	void recycle(String identity) throws Exception;
	
	/**删除指定记录
	 * @param identity 领域信息标识
	 */
	void delete(Object identity) throws Exception;

	/**
	 * 指定标识的领域对象是否存在
	 * 
	 * @param identity
	 * @return
	 */
	boolean isExist(Object identity) throws Exception;
	
	/**
	 * 获取匹配的领域对象
	 * @param map
	 * @return
	 */
	T get(Map<String, Object> map) throws Exception;
	
	/**
	 * 批量新增领域对象
	 * @param domainObjectList 领域对象集合
	 */
	void batchInsert(List<T> domainObjectList) throws Exception;
	
	/**
	 * 批量更新领域对象
	 * @param domainObjectList 领域对象集合
	 */
	void batchUpdate(List<T> domainObjectList) throws Exception;
	
	/**
	 * 统计匹配的记录数
	 * @param map 匹配条件
	 * @return 匹配的记录数
	 */
	long count(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据Id列表获取对应的领域对象列表
	 * @param idList Id列表
	 * @return 领域对象列表
	 */
	List<T> getByIdList(List<Object> idList) throws Exception;
	
	/**
	 * 获取除了Id列表对应对应的领域对象列表
	 * @param besideIdList Id列表
	 * @param enabled 是否启用
	 * @return 领域对象列表
	 */
    List<T> getByBesideIdList(List<Object> besideIdList) throws Exception;
}
