package com.bfxy.base.rabbit.broker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Kylin Su
 * @date 2021/2/16 6:53 PM
 */
@Slf4j
public class AsyncBaseQueue {

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_SIZE = 10000;

    private static ExecutorService senderAsync =
            new ThreadPoolExecutor(THREAD_SIZE,
                    THREAD_SIZE,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_SIZE),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setName("rabbitmq_client_async_sender");
                            return t;
                        }
                    },
                    new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                            log.error("async sender is error rejected, runnable: {}, executor: {}", r, executor);
                        }
                    }
            );
    public static void submit(Runnable runnable) {
        senderAsync.submit(runnable);
    }
}
