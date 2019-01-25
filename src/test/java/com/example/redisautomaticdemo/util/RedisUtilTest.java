package com.example.redisautomaticdemo.util;

import com.example.redisautomaticdemo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.TestAnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void expire() {
		Boolean isSucceed = redisUtil.expire("testStringKey", 600000);
		Assert.assertTrue(isSucceed);
	}

	@Test
	public void getExpire() {
		long expireTime = redisUtil.getExpire("testStringKey");
		System.out.println(expireTime);

	}

	@Test
	public void hasKey() {
		boolean hasKey = redisUtil.hasKey("testStringKey");
		Assert.assertTrue(hasKey);
	}

	@Test
	public void del() {
		redisUtil.del("testStringKey", "testStringKey1");
	}

	@Test
	public void get() {
		Object value = redisUtil.get("testStringKey");
		System.out.println(String.valueOf(value));
	}

	@Test
	public void set() {
		Boolean isSucceed = redisUtil.set("testStringKey1", "testStringValue1");
		Assert.assertTrue(isSucceed);
	}

	@Test
	public void setObject() {

		User user = new User();
		user.setUsername("Caroline");

		Boolean isSucceed = redisUtil.setObject("user", user, 60000);
		Assert.assertTrue(isSucceed);
	}

	@Test
	public void incr() {

		long seq = redisUtil.incr("sequence", 2);

		System.out.println(seq);
	}

	@Test
	public void decr() {
	}

	@Test
	public void hget() {
	}

	@Test
	public void hmget() {
	}

	@Test
	public void hmset() {
	}

	@Test
	public void hmset1() {
	}

	@Test
	public void hset() {
	}

	@Test
	public void hset1() {
	}

	@Test
	public void hdel() {
	}

	@Test
	public void hHasKey() {
	}

	@Test
	public void hincr() {
	}

	@Test
	public void hdecr() {
	}

	@Test
	public void sGet() {
	}

	@Test
	public void sHasKey() {
	}

	@Test
	public void sSet() {
	}

	@Test
	public void sSetAndTime() {
	}

	@Test
	public void sGetSetSize() {
	}

	@Test
	public void setRemove() {
	}

	@Test
	public void lGet() {
	}

	@Test
	public void lGetListSize() {
	}

	@Test
	public void lGetIndex() {
	}

	@Test
	public void lSet() {
	}

	@Test
	public void lSet1() {
	}

	@Test
	public void lSet2() {
	}

	@Test
	public void lSet3() {
	}

	@Test
	public void lUpdateIndex() {
	}

	@Test
	public void lRemove() {
	}
}