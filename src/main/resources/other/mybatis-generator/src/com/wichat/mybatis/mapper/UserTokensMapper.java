package com.wichat.mybatis.mapper;

import com.wichat.entity.UserTokens;

public interface UserTokensMapper {
    /**
     * Base Method.
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Base Method.
     */
    int insert(UserTokens record);

    /**
     * Base Method.
     */
    int insertSelective(UserTokens record);

    /**
     * Base Method.
     */
    UserTokens selectByPrimaryKey(Integer id);

    /**
     * Base Method.
     */
    int updateByPrimaryKeySelective(UserTokens record);

    /**
     * Base Method.
     */
    int updateByPrimaryKey(UserTokens record);
}