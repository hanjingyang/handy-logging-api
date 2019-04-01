package com.tinklabs.handy.logs.redis.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tinklabs.handy.base.cache.service.EntityCacheService;
import com.tinklabs.handy.logs.App;

import cn.hutool.json.JSONUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class EntityCacheServiceTest {
	
	@Autowired
	private EntityCacheService entityCacheService;
	
	@Test
	public void testPutAll() {
		String entityKey = "user_001";
		Map<String, String> entity = new HashMap<>();
		entity.put("name", "tom");
		entity.put("age", "1");
		entity.put("sex", "m");
		entityCacheService.putAll(entityKey, entity);
	}

	@Test
	public void testPut() {
		String entityKey = "user_001";
		entityCacheService.put(entityKey, "age", "3");
	}
	
	@Test
	public void testGetAll() {
		String entityKey = "user_001";
		Map<Object, Object> map = entityCacheService.getAll(entityKey);
		System.out.println(JSONUtil.toJsonStr(map));
		assertNotNull(map);
		assertNotNull(map.get("name"));
		
	}
	
	@Test
	public void testGet() {
		String entityKey = "user_001";
		Object name = entityCacheService.get(entityKey,"name");
		System.out.println(JSONUtil.toJsonStr(name));
		assertNotNull(name);
		assertNotNull(name);
	}
	
	@Test
	public void testKeys() {
		String entityKey = "user_001";
		Set<Object> fields = entityCacheService.keys(entityKey);
		System.out.println(JSONUtil.toJsonStr(fields));
		assertNotNull(fields);
		assertEquals(3, fields.size());
	}

	@Test
	public void testDeletet() {
		String entityKey = "user_001";
		entityCacheService.delete(entityKey);
		
	}

}
