package websiteVote;

import java.util.Map;

public class RedisTest {
    public static void main(String[] args) {
        WebVote vote = new WebVote();
        vote.publish("文章2","内容2","www2");
//        Map<String,String> articles = vote.getArticle("123");
//        for (Map.Entry<String,String> entry : articles.entrySet()){
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
    }
}
