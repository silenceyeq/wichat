package com.wichat.entity;

public class User {

    private Integer id;
    private String account;
    private String password;

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

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + "]";
	}
    
}