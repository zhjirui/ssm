package com.demo.controller;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by zhaojr on 2019/2/18.
 */
public interface BaseJob extends Job{
    public void execute(JobExecutionContext context) throws JobExecutionException;
}
