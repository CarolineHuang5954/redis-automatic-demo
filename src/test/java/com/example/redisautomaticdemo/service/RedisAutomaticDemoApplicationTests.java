package com.example.redisautomaticdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisAutomaticDemoApplicationTests {

	@Autowired
	private TestService testService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getIdTest() {
		for (int i = 0; i < 200; i++) {
			System.out.println(testService.getId());
		}
	}
}

