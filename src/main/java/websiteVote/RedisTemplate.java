package websiteVote;

import redis.clients.jedis.Jedis;

/**
 * @author Young
 * redis数据源
 */
public class RedisTemplate {
    public static Jedis redisTemplate;
    static {
        redisTemplate = new Jedis("127.0.0.1",6379);
        redisTemplate.auth("redispassword");
    }
}
