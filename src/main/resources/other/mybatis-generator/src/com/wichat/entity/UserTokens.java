package com.wichat.entity;

import java.util.Date;

public class UserTokens {

    private Integer id;
    private Integer userId;
    private String userAgent;
    private String token;
    private String type;
    private Date createTime;
    private Integer expires;

    /**
     * 主键
     *
     * @return the value of user_tokens.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     *
     * @param id the value for user_tokens.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     *
     * @return the value of user_tokens.c_user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     *
     * @param userId the value for user_tokens.c_user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 
     *
     * @return the value of user_tokens.c_user_agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 
     *
     * @param userAgent the value for user_tokens.c_user_agent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 
     *
     * @return the value of user_tokens.c_token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     *
     * @param token the value for user_tokens.c_token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     *
     * @return the value of user_tokens.c_type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     *
     * @param type the value for user_tokens.c_type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     *
     * @return the value of user_tokens.c_create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     *
     * @param createTime the value for user_tokens.c_create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     *
     * @return the value of user_tokens.c_expires
     */
    public Integer getExpires() {
        return expires;
    }

    /**
     * 
     *
     * @param expires the value for user_tokens.c_expires
     */
    public void setExpires(Integer expires) {
        this.expires = expires;
    }
}