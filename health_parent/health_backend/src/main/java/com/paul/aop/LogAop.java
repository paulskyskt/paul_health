package com.paul.aop;


import com.paul.pojo.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Think
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    /**
     * 访问时间
     */
    private Date visitTime;

    /**
     * 访问的类
     *
     * @param joinPoint
     */
    private Class executionClass;

    /**
     * 访问的方法
     *
     * @param joinPoint
     */
    private Method executionMethod;


    /**
     * 前置通知
     * 主要获取访问时间、访问的类、访问的方法
     *
     * @param jp
     */
    @Before("execution(* com.paul.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //访问时间
        visitTime = new Date();
        //访问的类
        executionClass = jp.getTarget().getClass();
        //获取方法的参数
        Object[] args = jp.getArgs();
        //获取方法名
        String methodName = jp.getSignature().getName();
        //判断获取无参方法/有参方法
        if ( args == null || args.length == 0 ) {
            //访问的方法
            executionMethod = executionClass.getMethod(methodName);
        } else {
            //获取有参数的方法，需要获取参数对应的class
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            //访问的方法
            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }


    /**
     * 后置通知
     * 主要获取日志中其它信息， 当前操作者 , 执行时长、 ip、 url
     *
     * @param jp
     */
    @After("execution(* com.paul.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        //获取当前操作者
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();
        //获取访问ip
        String ip = request.getRemoteAddr();
        //获取执行时长
        Long executionTime = new Date().getTime() - visitTime.getTime();
        //获取url
        //判断 排除当前类
        String url = "";
        if ( executionClass != null && executionMethod != null && executionClass != LogAop.class ) {
            //反射获取 类上的 @RequestMapping
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            //判断
            if(classAnnotation!=null){
                //获取注解上的值
                String[] classValue = classAnnotation.value();
                //判断
                if(executionMethod != null){
                    //执行方法上的@RequestMapping
                    RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                    //判断
                    if(methodAnnotation!=null){
                        //获取值
                        String[] methodValue = methodAnnotation.value();
                        //获取url
                        url = classValue[0]+methodValue[0];
                    }
                }
            }
        }
        //封装成SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]"+executionClass.getName()+"[方法名]"+executionMethod.getName());
        sysLog.setVisitTime(visitTime);
        sysLog.setUsername(username);
        sysLog.setUrl(url);
        //调用业务层存入数据库
        System.out.println(sysLog);


    }
}
