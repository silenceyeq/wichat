package com.wichat.mybatis.mapper;

import java.util.Map;

import com.wichat.entity.UserToken;

public interface UserTokenMapper {
	
	UserToken queryByCondition(Map<String, Object> params);
	
    /**
     * Base Method.
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Base Method.
     */
    int insert(UserToken record);

    /**
     * Base Method.
     */
    int insertSelective(UserToken record);

    /**
     * Base Method.
     */
    UserToken selectByPrimaryKey(Integer id);

    /**
     * Base Method.
     */
    int updateByPrimaryKeySelective(UserToken record);

    /**
     * Base Method.
     */
    int updateByPrimaryKey(UserToken record);
}