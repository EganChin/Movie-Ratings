package com.mr.web.config;

import com.mr.auth.AuthFilter;
import com.mr.web.auth.AuthRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LiangYongjie
 * @date 2019-01-06
 */
//@Configuration
public class ShiroConfig {

    private static final String[] DEFAULT_ANON_URLS = new String[]{
        "/**/*.ico",
        "/**/*.html",
        "/**/*.css",
        "/**/*.js",
        "/**/*.jpg",
        "/**/*.gif",
        "/**/*.png",
        "/fonts/**",
        "/druid/**",
        "/api/**",
        "/**/heapdump",
        "/**/loggers",
        "/**/liquibase",
        "/**/logfile",
        "/**/flyway",
        "/**/auditevents",
        "/**/jolokia",
        "/metrics",
        "/actuator/**",
        "/v2/**",
        "/swagger-resources/**",
        "/webjars/springfox-swagger-ui/**",
        "/**/swagger-ui.html",
    };

    private static final String[] CUSTOM_ANON_URLS = new String[]{
        "/u/sign_in",
        "/u/sign_up",
        "/u/find",
        "/code/phone",
        "/pay/notify",
        "/pay/return",
    };

    /**
     * 配置 shiro 管理器
     *
     * @param authRealm 自定义 realm
     * @return shiro 管理器
     */
    @Bean("securityManager")
    public SecurityManager securityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }

    /**
     * 配置 shiro 过滤器
     *
     * @param securityManager shiro 管理器
     * @return shiro 过滤器
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        // 添加过滤器
        Map<String, Filter> filters = new HashMap<>();
        filters.put("auth", new AuthFilter());
        shiroFilter.setFilters(filters);

        // 拦截规则
        Map<String, String> filterMap = new LinkedHashMap<>();

        // anon权限访问地址
        Arrays.stream(DEFAULT_ANON_URLS).forEach(e -> filterMap.put(e, "anon"));
        Arrays.stream(CUSTOM_ANON_URLS).forEach(e -> filterMap.put(e, "anon"));

        // auth权限访问地址
        filterMap.put("/**", "auth");

        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 配置认证通知器
     *
     * @param securityManager shiro 管理器
     * @return 认证通知器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
