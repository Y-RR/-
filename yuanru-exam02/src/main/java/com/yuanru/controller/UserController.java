package com.yuanru.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yuanru.bean.User;
import com.yuanru.utils.SqlMain;
import com.yuanru.utils.TestStringUtils;

@Controller
public class UserController {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	//使用JDK系列化方式保存十万个user随机对象到Redis
	@RequestMapping("testjdk")
	public String testjdk() throws UnsupportedEncodingException {
		
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		long begin = System.currentTimeMillis();
		
		for(int i = 0; i<100000; i++) {
			User user = new User();
			user.setUid(i+1);
			user.setName(SqlMain.getChinaName());
			user.setPhone("13"+TestStringUtils.getRandomString(9));
			user.setBirthday(new Date());
			opsForList.leftPush(i+1+"", user);
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
		return "list";
	}
	
//使用JSON系列化方式保存十万个user随机对象到Redis
	@RequestMapping("testjson")
	public String testjson() throws UnsupportedEncodingException {
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		long begin = System.currentTimeMillis();
		for(int i = 0; i<100000; i++) {
			User user = new User();
			user.setUid(i+1);
			user.setName(SqlMain.getChinaName());
			user.setPhone("13"+TestStringUtils.getRandomString(9));
			user.setBirthday(new Date());
			Object json = JSON.toJSON(user);
			opsForList.leftPush(i+1+"", json);
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
		return "list";
	}
	
	//使用Redis的Hash类型保存十万个user随机对象到Redis
	@RequestMapping("testHash")
	public String testHash() throws UnsupportedEncodingException {
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		long begin = System.currentTimeMillis();
		
		Map<String,Map<String,Object>> m = new HashMap<>();
		
		for(int i = 0; i<100000; i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("id", i+1);
			map.put("name", SqlMain.getChinaName());
			map.put("phone", "13"+TestStringUtils.getRandomString(9));
			map.put("bir", new Date());
			m.put("i+1", map);
		}
		opsForHash.putAll("user", m);
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
		return "list";
	}
	
}
