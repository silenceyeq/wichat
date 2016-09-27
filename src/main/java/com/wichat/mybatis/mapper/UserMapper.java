package com.wichat.mybatis.mapper;

import java.util.Map;

import com.wichat.entity.User;

public interface UserMapper {
	
	User queryByCondition(Map<String, Object> params);
	
    /**
     * Base Method.
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Base Method.
     */
    int insert(User record);

    /**
     * Base Method.
     */
    int insertSelective(User record);

    /**
     * Base Method.
     */
    User selectByPrimaryKey(Integer id);

    /**
     * Base Method.
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * Base Method.
     */
    int updateByPrimaryKey(User record);
}