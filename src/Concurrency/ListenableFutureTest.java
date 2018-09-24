package Concurrency;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * Created by bin_chen on 2018/9/20.
 */
public class ListenableFutureTest {
    public static void main(String[] args) {
        //真正干活的线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new CustomizableThreadFactory("demo"),
                new ThreadPoolExecutor.DiscardPolicy());

        //guava的接口ListeningExecutorService继承了jdk原生ExecutorService接口，重写了submit方法，修改返回值类型为ListenableFuture
        ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(poolExecutor);

        int times = 20;
        //可以用CountDownLatch来控制异步的多种、多个任务都已完成才继续进行主线程
        final CountDownLatch latch = new CountDownLatch(times);
        for (int i = 0; i < times; i++) {
            ListenableFuture<String> businessFuture = listeningExecutor.submit(new BusinessTask("BusinessTask" + i));
            businessFuture.addListener(() -> {
                try {
                    //处理异步结果
                    System.out.println(businessFuture.get(3000, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    //记日志
                    System.out.println(e.getMessage());
                } finally {
                    latch.countDown();
                }
            }, listeningExecutor);

            //用于展示submit异步任务的性能
            System.out.println(i);
        }

        try {
            System.out.println("await...");
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }
}

class BusinessTask implements Callable<String> {
    private String str;

    public BusinessTask(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        //处理业务逻辑
        System.out.println(str);
        return "return";
    }
}