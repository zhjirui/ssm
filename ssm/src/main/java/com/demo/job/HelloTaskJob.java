package com.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Calendar;

/**
 * Created by zhaojr on 18-12-5.
 */
public class HelloTaskJob implements Job{

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime() + "定时服务");
    }
}
