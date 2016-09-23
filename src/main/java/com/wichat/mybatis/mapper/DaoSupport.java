package com.wichat.mybatis.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wichat.mybatis.DomainObject;

public interface DaoSupport<T extends DomainObject> {

	/**
	 * 获取领域对象信息
	 * 
	 * @param identity
	 *            领域信息标识
	 * @return
	 */
	T get(Object identity);

	/**
	 * 添加领域对象信息
	 * 
	 * @param domainObj
	 *            领域对象
	 */
	Serializable insert(T domainObj);

	/**
	 * 修改领域对象信息
	 * 
	 * @param domainObj
	 *            领域对象
	 */
	void update(T domainObj);

	/**
	 * 逻辑删除领域对象
	 * 
	 * @param identity
	 *            领域对象标识
	 */
	void recycle(Object identity);

	/**
	 * 删除指定记录
	 * 
	 * @param identity
	 */
	void delete(Object identity);

	/**
	 * 指定的领域对象是否存在
	 * 
	 * @param identity
	 * @return
	 */
	boolean isExist(T domainObj);

	/**
	 * 指定标识的领域对象是否存在
	 * 
	 * @param identity
	 * @return
	 */
	boolean isExist(Object identity);

	/**
	 * 批量添加领域对象信息
	 * 
	 * @param list
	 */
	void batchInsert(List<T> list);

	/**
	 * 批量更新领域对象信息
	 * 
	 * @param list
	 */
	void batchUpdate(List<T> list);

	/**
	 * 获取所有领域对象
	 * 
	 */
	List<T> getAll();

	/**
	 * 获取所有已分页的领域对象
	 * 
	 * @param paginationInfo
	 *            分页信息
	 */
//	List<T> getAll(PaginationInfo paginationInfo);

	/**
	 * 获取指定条件（属性值相等）的领域对象集合
	 * 
	 * @param map
	 *            领域对象属性
	 * @return
	 */
	
	List<T> getList(Map<String, Object> map);
		
	/**
	 * 根据Id列表获取对应的领域对象列表
	 * @param idList Id列表
	 * @return 领域对象列表
	 */
	List<T> getByIdList(List<Object> idList);
	
	/**
	 * 获取除了Id列表对应对应的领域对象列表
	 * @param besideIdList Id列表
	 * @param enabled 是否启用
	 * @return 领域对象列表
	 */
	public List<T> getByBesideIdList(List<Object> besideIdList);

	/**
	 * 根据条件统计记录数量
	 * 
	 * @param map
	 * @return
	 */
	long count(Map<String, Object> map);

	/**
	 * 获取指定条件（属性值相等）的领域对象集合（分页）
	 * 
	 * @param map
	 *            领域对象属性
	 * @param paginationInfo
	 *            分页信息
	 * @return
	 */
//	CollectionWithPagination<T> getListWithPagination(Map<String, Object> map,
//			PaginationInfo paginationInfo);

	/**
	 * 根据指定条件获取领域对象信息，若没有相应对象则返回null。指定条件必须具有唯一性。
	 * 
	 * @param map
	 *            领域对象属性
	 * @return
	 */
	T get(Map<String, Object> map);
}
