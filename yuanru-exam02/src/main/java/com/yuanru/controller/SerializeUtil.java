package com.yuanru.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.yuanru.bean.User;

public class SerializeUtil {

	
	@Test
	public void testria() {
		User Po = new User(1,"小明","12376584598",new Date());
		
		List<Object> list = new ArrayList<>();
		for(int i = 0; i<100000; i++) {
			list.add(list);
		}
		
		JdkSerializationRedisSerializer j = new JdkSerializationRedisSerializer();
		GenericJackson2JsonRedisSerializer g = new GenericJackson2JsonRedisSerializer();
		
		
		long aa = System.currentTimeMillis();
		byte[] bs = j.serialize(list);
		System.out.println("JdkSerializationRedisSerializer序列化后时间为:"+(System.currentTimeMillis()-aa)+"ms"+"长度为:"+bs.length);
		
		long bb = System.currentTimeMillis();
		j.deserialize(bs);
		System.out.println("JdkSerializationRedisSerializer反序列化的时间为:"+(System.currentTimeMillis()-bb));
		
		long aaa = System.currentTimeMillis();
		byte[] bs2 = g.serialize(list);
		System.out.println("GenericJackson2JsonRedisSerializer序列化后时间为:"+(System.currentTimeMillis()-aaa)+"ms"+"长度为:"+bs2.length);
		
		long bbb = System.currentTimeMillis();
		g.deserialize(bs2);
		System.out.println("GenericJackson2JsonRedisSerializer序列化后时间为:"+(System.currentTimeMillis()-bbb));
	}
}
