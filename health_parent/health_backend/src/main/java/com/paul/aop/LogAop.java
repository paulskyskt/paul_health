package com.paul.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;

    @Before("execution(* com.paul.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint){

        visitTime = new Date();

        System.out.println(visitTime);
    }
}
