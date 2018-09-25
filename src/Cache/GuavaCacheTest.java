package Cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * create by chenjiayang on 2018/9/16
 */
public class GuavaCacheTest {
    private static Logger logger = Logger.getLogger(GuavaCacheTest.class.getName());

    public static void main(String[] args) {
        /**
         * A LoadingCache is a Cache built with an attached CacheLoader
         */
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "value";
                    }
                });
        try {
            //This will either return an already cached value, or else use the
            // cache's CacheLoader to atomically load a new value into the cache
            // 如果 Key 不存在的话通过 load 方法返回 value
            String res = cache.get("key");
            System.out.println(res);
        } catch (ExecutionException e) {
            logger.info(e.getMessage());
        }

        /**
         * get method with Callable
         */
        Cache<String, String> cache1 = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .initialCapacity(10)
                .concurrencyLevel(6)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .build();
        try {
            String res = cache1.getIfPresent("key");
            System.out.println(res);
            // get 方法第二个参数为 Callable 回调函数
            String res1 = cache1.get("key", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "value";
                }
            });
            System.out.println(res1);
        } catch (ExecutionException e) {
            logger.info(e.getMessage());
        }
    }
}
