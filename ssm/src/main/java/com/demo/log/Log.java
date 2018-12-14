package com.demo.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhaojr on 2018/12/14.
 */
@Aspect
@Component
public class Log {
    private final static Logger logger = LoggerFactory.getLogger(Log.class);

    @Pointcut("execution (* com.demo..*.*(..))")
    public void aspect(){
    }
    @Before("aspect()")
    public void before(JoinPoint joinPoint){
        logger.info("before...." + joinPoint);
    }
    @After("aspect()")
    public void after(JoinPoint joinPoint){
        logger.info("after...." + joinPoint);
    }
    @Around("aspect()")
    public void around(JoinPoint joinPoint){
        logger.info("around...." + joinPoint);
    }
}
