package com.demo.thread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by zhaojr on 2018/12/18.
 */
public class ThreadPoolTask implements Callable<List>, Serializable {

    private static final long serialVersionUID = 0;

    // 保存任务所需要的数据
    private Object threadPoolTaskData;

    public ThreadPoolTask(Object tasks) {
        this.threadPoolTaskData = tasks;
    }

    public synchronized List call() throws Exception {
        List list = (List) threadPoolTaskData;
        List returnList = new ArrayList();
        System.out.println("开始执行任务" );
        try {
            for ( int  i= 0 ; i< list.size() ; i++){
                System.out.println(((HashMap)list.get(i)).containsKey("1"));
                returnList.add(((HashMap)list.get(i)).containsKey("1"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
