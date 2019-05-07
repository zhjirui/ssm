package com.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors 是一个工厂类
 * 提供的是Executor、ExecutorService、ScheduledExecutorService、ThreadFactory 和 Callable 类的实例的工厂方法
 */
public class ExecutorsDemo {

    public void call(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        BatchMessageTask batchMessageTask = new BatchMessageTask("1", "test");
        executorService.execute(batchMessageTask);
    }

}
