package com.yaojialiu.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yaojialiu.domain.User;
import com.yjl.utils.RandomUitl;
import com.yjl.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans2.xml")
public class TestRedisJSONHash {
	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Test
	public void test_insert() {
		//map集合
		Map<String, User> user = new HashMap<String, User>();
		//存入十万条数据
		for (int i = 0; i < 100000; i++) {
			user.put("u_"+i,new User(i,StringUtil.generateChineseName()+StringUtil.randomChineseString(2),"女","13"+RandomUitl.randomString(9),"cdssvvf@qq.com","1998-02-22"));
		}
		//开始时间
		long start = System.currentTimeMillis();
		
		redisTemplate.opsForHash().putAll("user_keys", user);
		//结束时间
		long end = System.currentTimeMillis();
		
		System.out.println("JSONHash添加十万条数据耗时:"+(end-start));
	}
}
