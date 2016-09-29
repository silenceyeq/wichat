package com.wichat.mybatis.mapper;

import com.wichat.entity.User;

public interface UserMapper {
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