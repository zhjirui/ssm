package com.demo.test;

import com.demo.thread.ThreadPoolTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by zhaojr on 2018/12/18.
 */
@Controller
@RequestMapping("/testpool")
public class TestThreadPool {

    private static int produceTaskMaxNumber = 10;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    public void setThreadPoolTaskExecutor(
            ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public void TestThreadPool() {
        System.out.println("进入测试");
        List resultList = null;
        List<Map<String, String>> list = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> map1 = new HashMap<String, String>();
        map.put("1", "1");
        map.put("2", "2");
        list.add(map);
        list.add(map1);
        FutureTask<List> futureTask = new FutureTask<List>(
                new ThreadPoolTask(list));
        //threadPoolTaskExecutor.execute(futureTask);
        //future.get() == null则任务执行成功
        //Future future = threadPoolTaskExecutor.submit(futureTask);
        threadPoolTaskExecutor.submit(futureTask);
        try {
            // 取得结果，调用get方法则会一直等待线程返回才往下执行
            resultList = futureTask.get();
        } catch (Exception e) {
            System.out.println("获取异常：" + e.getLocalizedMessage());
        } finally {
            System.out.println("finally...");
        }

    }
}
