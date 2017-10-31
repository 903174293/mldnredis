package cn.mldn.mldnredis.jedis.test;

import org.junit.Test;

import cn.mldn.util.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class TestRedisConnection {
	@Test
	public void testConnection() {
		RedisConnectionUtil rcu = new RedisConnectionUtil() ;
		Jedis jedis = rcu.getConnection() ; // 获得连接信息
		System.err.println(jedis);
		jedis.close(); 
	}
}
