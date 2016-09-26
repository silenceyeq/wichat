package com.wichat.mybatis.mapper;

import com.wichat.pojo.WichatUser;

public interface WichatUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(WichatUser record);

    int insertSelective(WichatUser record);

    WichatUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(WichatUser record);

    int updateByPrimaryKey(WichatUser record);
}