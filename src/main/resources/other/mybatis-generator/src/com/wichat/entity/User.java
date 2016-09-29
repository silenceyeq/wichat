package com.wichat.entity;

import java.util.Date;

public class User {

    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private String avatarUrl;
    private Date createTime;
    private Integer effective;

    /**
     * 主键
     *
     * @return the value of user.u_id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     *
     * @param id the value for user.u_id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 账号
     *
     * @return the value of user.u_account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     *
     * @param account the value for user.u_account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 密码
     *
     * @return the value of user.u_password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     *
     * @param password the value for user.u_password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 昵称
     *
     * @return the value of user.u_nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     *
     * @param nickname the value for user.u_nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 头像地址
     *
     * @return the value of user.u_avatar_url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 头像地址
     *
     * @param avatarUrl the value for user.u_avatar_url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 创建时间
     *
     * @return the value of user.u_create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime the value for user.u_create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 是否生效 1:是 0:否
     *
     * @return the value of user.u_effective
     */
    public Integer getEffective() {
        return effective;
    }

    /**
     * 是否生效 1:是 0:否
     *
     * @param effective the value for user.u_effective
     */
    public void setEffective(Integer effective) {
        this.effective = effective;
    }
}