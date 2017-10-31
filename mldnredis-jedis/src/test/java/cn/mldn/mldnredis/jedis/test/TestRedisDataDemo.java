package cn.mldn.mldnredis.jedis.test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.mldn.util.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class TestRedisDataDemo {
	public static Jedis jedis = null ;
	static {
		RedisConnectionUtil rcu = new RedisConnectionUtil() ;
		jedis = rcu.getConnection() ;
	} 
	@Test
	public void testKeys() throws Exception {
		jedis.flushDB() ;	// 清空一下数据库
		jedis.set("user-mldn", "MLDN用户") ;
		jedis.set("user-admin", "Admin用户") ;
		Set<String> keys = jedis.keys("user-*") ;
		Iterator<String> iter = keys.iterator() ;
		while (iter.hasNext()) {
			String key = iter.next() ;
			System.err.println(key + " = " + jedis.get(key));
		}
	}
	
	@Test
	public void testDataList() throws Exception {
		jedis.flushDB() ;	// 清空一下数据库
		jedis.lpush("user-mldn", "A","B","C") ;
		System.err.println(jedis.rpop("user-mldn"));
		System.err.println(jedis.rpop("user-mldn"));
		System.err.println(jedis.rpop("user-mldn"));
		System.err.println(jedis.rpop("user-mldn"));
	}
	@Test
	public void testDataHash() throws Exception {
		jedis.hset("user-mldn", "name", "约翰男子") ;
		jedis.hset("user-mldn", "age", String.valueOf(89)) ; 
		jedis.hset("user-mldn", "sex", "不男非女") ;
		System.err.println(jedis.hget("user-mldn", "name"));
	}
	@Test
	public void testDataA() throws Exception {
		// jedis.set("mldn", "helloworld") ;
		jedis.setex("mldn",3, "helloworld") ;
		TimeUnit.SECONDS.sleep(4);
		System.err.println(jedis.get("mldn")); 
	}
}
