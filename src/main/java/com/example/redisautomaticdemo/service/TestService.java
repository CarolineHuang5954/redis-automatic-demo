package com.example.redisautomaticdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 使用redis的RedisAtomicLong实现分布式系统自增序列
 *
 * @date: 2019/1/25 9:49
 * @author: 黄鑫淼
 * @version: 1.0
 */
@Component
public class TestService {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	public String getId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String formatDate = sdf.format(date);
		String key = "key" + formatDate;
		Long incr = getIncr(key, getCurrent2TodayEndMillisTime());
		if (incr == 0) {
			//从001开始
			incr = getIncr(key, getCurrent2TodayEndMillisTime());
		}
		//三位序列号
		DecimalFormat df = new DecimalFormat("000");
		return formatDate + df.format(incr);
	}

	public Long getIncr(String key, long liveTime) {
		RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		Long increment = entityIdCounter.getAndIncrement();
		//初始设置过期时间
		if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
			//单位毫秒
			entityIdCounter.expire(liveTime, TimeUnit.MILLISECONDS);
		}
		return increment;
	}

	/**
	 * 现在到今天结束的毫秒数
	 *
	 * @param
	 * @return java.lang.Long
	 * @author：黄鑫淼
	 * @date：2019/1/25 9:51
	 */
	public Long getCurrent2TodayEndMillisTime() {
		Calendar todayEnd = Calendar.getInstance();
		// Calendar.HOUR 12小时制
		// HOUR_OF_DAY 24小时制
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTimeInMillis() - System.currentTimeMillis();
	}
}