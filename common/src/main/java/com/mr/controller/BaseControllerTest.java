package com.mr.controller;

import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author Egan
 * @date 2019/4/22 18:29
 **/
public class BaseControllerTest {

    protected static Logger logger = LoggerFactory.getLogger("Unit-Test");

    private static String host;
    private static String token;

    public BaseControllerTest(String host, String token) {
        BaseControllerTest.host = host;
        BaseControllerTest.token = token;
    }

    @Autowired
    protected RestTemplate restTemplate;

    /**
     * 普通请求头，适用于url参数
     **/
    protected HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        return headers;
    }

    /**
     * json请求头，适用于json参数
     **/
    protected HttpHeaders getJsonHeader() {
        HttpHeaders headers = getHeader();
        headers.set("content-type", "application/json;charset=UTF-8");
        return headers;
    }


    /**
     * xml请求头
     **/
    protected HttpHeaders getXmlHeader() {
        HttpHeaders headers = getHeader();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        return headers;
    }

    /**
     * 适用于上传文件
     **/
    protected HttpHeaders getFileHeader() {
        HttpHeaders headers = getHeader();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return headers;
    }

    /**
     * http请求测试(传入参数自动拼接url)
     *
     * @param url    不含协议、主机名和端口号的部分url
     * @param method http方法
     * @param params 请求参数
     * @date 2019/4/22 20:07
     **/
    protected void requestAuto(String url, HttpMethod method, Object params) {

        HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
        Map map = new BeanMap(params);
        url = host + url;

        Set keys = map.keySet();
        if (keys.size() > 0) {
            Iterator iter = keys.iterator();
            StringBuilder urlBuilder = new StringBuilder(url);
            Object key = iter.next();
            urlBuilder.append("?").append(key).append("=").append(map.get(key));
            while (iter.hasNext()) {
                key = iter.next();
                urlBuilder.append("&")
                    .append(key)
                    .append("=")
                    .append(map.get(key));
            }
            url = urlBuilder.toString();
        }

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            url, method, requestEntity, String.class);


        print(url, requestEntity, responseEntity);
    }

    /**
     * http请求测试(手动拼接url)
     *
     * @param url    不含协议、主机名和端口号的部分url
     * @param method http方法
     * @param params 请求参数
     * @date 2019/4/22 20:07
     **/
    protected void requestManual(String url, HttpMethod method, Object... params) {
        HttpEntity<String> requestEntity = new HttpEntity<>(getHeader());
        url = host + url;
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            url, method, new HttpEntity<>(getHeader()), String.class, params);


        print(url, requestEntity, responseEntity);
    }

    /**
     * 上传文件专用
     **/
    protected void requestFile(String url, HttpMethod method, HttpEntity<MultiValueMap<String, Object>> requestEntity) {

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            host + url
            , method, requestEntity, String.class);

        print(url, requestEntity, responseEntity);
    }


    private void print(String url, HttpEntity requestEntity, ResponseEntity responseEntity) {
        logger.info("request-url:" + url);
        logger.info("request-header:" + requestEntity.getHeaders());
        logger.info("request-body:" + requestEntity.getBody());
        logger.info("response-status-code:" + responseEntity.getStatusCode());
        logger.info("response-header:" + responseEntity.getHeaders());
        logger.info("response-body:" + responseEntity.getBody());
    }

}
