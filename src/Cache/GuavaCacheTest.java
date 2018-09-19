package Cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

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

    }
}
