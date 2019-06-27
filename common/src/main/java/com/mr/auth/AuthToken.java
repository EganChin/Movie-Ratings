package com.mr.auth;

import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * shiro token类
 *
 * @author LiangYongjie
 * @date 2019-01-06
 */
public class AuthToken implements AuthenticationToken, Serializable {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
