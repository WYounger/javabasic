package redistest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class Redis1Th {

    public static Jedis jedis;

    @BeforeAll
    public static void setRedisConfig(){
        jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("redispassword");
    }

    @Test
    public void testString(){
        //存在该key，则覆盖; 不存在该key，则创建
        jedis.set("id","2");
        //获取
        print(jedis.get("id"));// 2
        //增加一定的增量
        jedis.incrBy("id",2L);
        print(jedis.get("id")); // 4
        //删除key值对
        jedis.del("id");
        print(jedis.get("id"));// null
    }

    @Test
    public void testList(){
        //不存在该key，则会自动创建
        //左进
        jedis.lpush("hobbies","basketball","swimming");
        //lrange(key,0,-1)返回该key的所有value
        print(jedis.lrange("hobbies",0,-1)); //[swimming, basketball]
        //右进
        jedis.rpush("hobbies","game");
        print(jedis.lrange("hobbies",0,-1));//[swimming, basketball, game]

        //右出
        jedis.rpop("hobbies");
        print(jedis.lrange("hobbies",0,-1));//[swimming, basketball]

        //左出
        jedis.lpop("hobbies");
        print(jedis.lrange("hobbies",0,-1));//[basketball]

        //取该key的指定的下标的值
        print(jedis.lindex("hobbies",0));//basketball

        //通过list的rpush/rpop和lpush/lpop可以模拟栈和队列
    }

    @Test
    public void testSet(){
        //key不存在创建并加入元素
        jedis.sadd("ids","1");
        print(jedis.sadd("ids","1"));// 0,有重复，添加失败
        jedis.sadd("ids","2");
        //查询该key对应的所有value
        print(jedis.smembers("ids")); //[1, 2]
        //删除指定key的value
        jedis.srem("ids","2");
        print(jedis.smembers("ids")); //[1]
        //判断该key是否存在该value
        print(jedis.sismember("ids","1")); // true
        print(jedis.smembers("ids")); //[1]
    }

    @Test
    public void testHash(){
        Map<String,String> hashValues = new HashMap<>(2);
        hashValues.put("name","young");
        hashValues.put("age","21");
        //key-hashValues
        jedis.hset("user",hashValues);
        //取key的多个subKey对应的值
        print(jedis.hmget("user","name","age")); //[young, 21]
        //取key所有subKey
        print(jedis.hkeys("user")); //[age, name]
        //取key所有subKey对应的value
        print(jedis.hvals("user")); //[21, young]
        jedis.hdel("user","age");
        //删除key的subkey-subValue
        print(jedis.hkeys("user")); //[ name]
    }

    @Test
    public void testZSet(){
        Map<String,Double> grades = new HashMap<>(2);
        grades.put("tom",85.0D);
        grades.put("bob",90.0D);
        grades.put("alice",80.0D);
        grades.put("henry",86.0D);
        grades.put("jerry",91.0D);
        jedis.zadd("game",grades);
       //返回按照score排序(升序)后名次在start~stop的member
        print(jedis.zrange("game",0,2)); //[alice, tom, henry]
        //返回按照score在min~max的member
        print(jedis.zrangeByScore("game",80,90)); //[alice, tom, henry, bob]
    }
    public static void print(Object o){
        System.out.println(o);
    }
}
