package cn.mldn.util;

import redis.clients.jedis.Jedis;

public class RedisConnectionUtil {
	private static final String REDIS_HOST = "192.168.68.139" ;	// 主机名称
	private static final int REDIS_PORT = 6379 ;	// 端口名称
	private static final String REDIS_AUTH = "mldnjava" ;	// 认证信息
	private Jedis jedis ; // 这个对象主要就是连接对象信息
	public RedisConnectionUtil() {	// 构造方法之中进行数据库的连接
		this.jedis = new Jedis(REDIS_HOST,REDIS_PORT) ; // 输入主机和端口名称
		this.jedis.auth(REDIS_AUTH) ; // Redis设置了认证信息
	}
	public Jedis getConnection() {
		return this.jedis ;
	} 
	public void close() {
		this.jedis.close(); 
	}
}
