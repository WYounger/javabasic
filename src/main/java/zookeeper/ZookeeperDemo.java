package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.jupiter.api.Test;

public class ZookeeperDemo {
    @Test
    public void testCOnnection() {
        String connectionString = "47.114.162.77:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 2);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);

        client.start();
        try {

            PathChildrenCache childrenCache = new PathChildrenCache(client,"/test",true);
            childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            childrenCache.getListenable().addListener((curatorFramework, pathChildrenCacheEvent) ->
                    System.out.println(pathChildrenCacheEvent.getData().getPath()));
            Thread.sleep(200 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * for(;;){
     *     old = get();
     *     new = operation(old)
     *     if(compareAndSet(old,new)){
     *         return new;
     *     }
     * }
     */
}
