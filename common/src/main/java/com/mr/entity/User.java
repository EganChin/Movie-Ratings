package com.mr.entity;


import java.math.BigDecimal;

/**
 * @author LiangYongjie
 * @date 2019-01-06
 */
public class User {

	private Integer id;

	private String phone;

	/**
	 * 昵称
	 */
	private String nickname;

	private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
