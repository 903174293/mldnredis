package cn.mldn.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionPoolUtil {
	private static final String REDIS_HOST = "192.168.68.139" ;	// 主机名称
	private static final int REDIS_PORT = 6379 ;	// 端口名称
	private static final int TIMEOUT = 2000 ; // 连接超时时间
	private static final int MAX_TOTAL = 200 ;	// 最多允许200个的连接
	private static final int MAX_IDLE = 20 ;	// 没有访问时的最小维持数量
	private static final int MAX_WAIT_MILLIS = 1000 ;	// 最大等待时间
	private static final boolean TEST_ON_BORROW = true ;	// 是否要进行连接测试
	private static final String REDIS_AUTH = "mldnjava" ;	// 认证信息
	private JedisPool pool = null ;	// 连接池对象
	public RedisConnectionPoolUtil() {	// 构造方法之中进行数据库的连接
		// 如果要想使用连接池进行控制，那么一定需要进行连接池的相关配置
		JedisPoolConfig config = new JedisPoolConfig() ;	// 进行连接池配置
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT_MILLIS);
		config.setTestOnBorrow(TEST_ON_BORROW); // 测试通过后返回可用连接
		this.pool = new JedisPool(config,REDIS_HOST,REDIS_PORT,TIMEOUT,REDIS_AUTH) ;
	}
	public Jedis getConnection() {
		return this.pool.getResource() ; // 连接池获取连接
	} 
	public void close() {
		this.pool.close(); 	// 连接池关闭
	}
}
