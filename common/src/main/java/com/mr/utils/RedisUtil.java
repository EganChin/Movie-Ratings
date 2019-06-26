package com.mr.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Egan
 * @date 2019/3/10 14:44
 **/
@Component
public class RedisUtil {

	public static RedisTemplate<String, Object> t;

	private static final String TOKENS = "tokenTable";

	@Autowired
	public RedisUtil(RedisTemplate<String, Object> template){
		RedisUtil.t = template;
	}

	public static ValueOperations<String, Object> value(){
		return t.opsForValue();
	}

	public static void deleteAllToken(Integer id) {
		HashOperations<String, Object, Object> hash = t.opsForHash();
		List<String> tokens = (List<String>) hash.get(TOKENS, id.toString());
		if (tokens != null) {
			t.delete(tokens);
			hash.delete(TOKENS, id.toString());
		}
	}

	public static void addToken(Integer id, String token) {
		HashOperations<String, String, Object> hashOperations = t.opsForHash();
		List<String> tokens = (List<String>) hashOperations.get(TOKENS, id.toString());
		if (tokens == null) {
			tokens = new ArrayList<>();
		} else {
			// 清除过期token
			List<String> timeout = new ArrayList<>();
			Iterator<String> iterator = tokens.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				if (!t.hasKey(next)) {
					timeout.add(next);
					iterator.remove();
				}
			}
			if (timeout.size() != 0) {
				t.delete(timeout);
			}
		}
		tokens.add(token);
		hashOperations.put(TOKENS, id.toString(), tokens);
	}

	public static BigDecimal getPublisherPrice(){
		return (BigDecimal) value().get("pprice");
	}

	public static BigDecimal getUserPrice(){
		return (BigDecimal) value().get("cprice");
	}

	public static Integer timeout4(){return (Integer) value().get("timeout4");}
	public static Integer timeout5(){return (Integer) value().get("timeout5");}
}
