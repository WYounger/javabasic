package websiteVote;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebVote {
    private Jedis redisTemplate;
    public WebVote(){
        redisTemplate = RedisTemplate.redisTemplate;
    }

    /**
     * 文章的发布
     * @param title
     * @param content
     * @param link
     * @return 返回文章的Id
     */
    public Long publish(String title,String content,String link){
        Map<String,String> map = new HashMap<>(3);
        map.put("title",title);
        map.put("content",content);
        map.put("link",link);
        //获取文章id; 首次运行时,key:articleId不存在,自动创建articleId:0
        Long id = Long.valueOf(redisTemplate.incr("articleId"));
        redisTemplate.hset(id.toString(),map);
        return id;
    }

    /**
     * 依据文章id获取文章信息
     * @param id
     * @return
     */
    public Map<String,String> getArticleDetail(String id){
        return redisTemplate.hgetAll(id);
    }

    /**
     * 给文章分类(一个可能属于几个分类)
     * @param id
     * @param toSet 分类数组
     */
    public void setCategory(String id,String[] toSet){
        for (String category: toSet){
            redisTemplate.sadd(category,id);
        }
    }

    /**
     * 获取分类的下所有文章id
     * @param category
     * @return
     */
    public Set<String> getCategoryArticles(String category){
        return redisTemplate.smembers(category);
    }
}
