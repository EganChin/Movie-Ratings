package com.mr.web.auth;


import com.mr.auth.AuthToken;
import com.mr.config.AuthConfig;
import com.mr.entity.User;
import com.mr.exception.RRException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * shiro 数据存储
 *
 * @author LiangYongjie
 * @date 2019-01-06
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private AuthConfig configuration;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * token 类型支持
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    /**
     * 授权（验证权限时调用）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return info;
    }

    /**
     * 认证（登录时调用）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getCredentials();

        /*
         * 管理员在使用时，刷新令牌过期时间
         */
        User user = (User) redisTemplate.opsForValue().get(accessToken);
        if (user == null) {
            throw new RRException("token过期", 401);
        } else{
            redisTemplate.expire(accessToken, configuration.getTimeout().getSeconds(), TimeUnit.SECONDS);
        }

        return new SimpleAuthenticationInfo(user, accessToken, getName());
    }
}
