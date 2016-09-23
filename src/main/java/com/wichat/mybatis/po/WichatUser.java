package com.wichat.mybatis.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wichat.mybatis.DomainObject;

/**
 * 用户表 实体类
 * 
 * @author 2016-09-23 05:37:58 zrr
 */
@Entity
@Table(name = "wichat_user")
public class WichatUser extends DomainObject implements Serializable{

	// 账号
	private String account;
	// 密码
	private String password;

	@Transient
	@Override
	public Object getId() {
		return this.account;
	}

	/**
	 * Get 账号
	 */
	@Id
	@GeneratedValue
	@Column(name = "account")
	public String getAccount() {
		return account;
	}

	/**
	 * Set 账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Get 密码
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	/**
	 * Set 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
