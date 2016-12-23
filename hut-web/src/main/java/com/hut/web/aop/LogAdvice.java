package com.hut.web.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Jared on 2016/12/23.
 */
@Component
@Aspect
public class LogAdvice {

    @Pointcut("execution(com.hut.web.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(){
        System.out.println("方法执行前");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("方法执行后");
    }

}
