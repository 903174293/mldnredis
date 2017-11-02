package cn.mldn.mldnredis.springdata;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedisTemplateHashDemo {	// Redis操作用RedisTemplate整体处理
	@Resource
	private RedisTemplate<String,String> redisTemplate ;
	@Test
	public void testSet() {
		this.redisTemplate.opsForHash().put("mldn-hello", "name", "阿尤");
		this.redisTemplate.opsForHash().put("mldn-hello", "age", "77");
	} 
	@Test 
	public void testGet() {
		System.err.println(this.redisTemplate.opsForHash().get("mldn-hello", "name"));
		System.err.println(this.redisTemplate.opsForHash().get("mldn-hello", "age"));
	}
}
