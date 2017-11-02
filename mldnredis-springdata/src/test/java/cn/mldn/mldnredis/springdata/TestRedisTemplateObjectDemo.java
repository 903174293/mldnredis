package cn.mldn.mldnredis.springdata;

import java.io.Serializable;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@SuppressWarnings("serial")
class Member implements Serializable {
	private String mid ;
	private String sex ;
	private Integer age ;
	public Member(String mid,String sex,Integer age) {
		this.mid = mid ;
		this.sex = sex ;
		this.age = age ;
	}
	@Override
	public String toString() {
		return "Member [mid=" + mid + ", sex=" + sex + ", age=" + age + "]";
	}
}
@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedisTemplateObjectDemo {	// Redis操作用RedisTemplate整体处理
	@Resource
	private RedisTemplate<String,Member> redisTemplate ;
	@Test
	public void testSet() {
		this.redisTemplate.opsForValue().set("mldn-member", new Member("小李","男",16));
	}
	@Test  
	public void testGet() { 
		System.err.println(this.redisTemplate.opsForValue().get("mldn-member"));
	}
}
