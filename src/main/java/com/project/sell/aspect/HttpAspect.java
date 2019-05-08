package com.project.sell.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class HttpAspect {
    @Pointcut("execution(public * com.project.sell.Controller.BuyerOrderController.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinpoint){
        ServletRequestAttributes attributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        log.info("url={}",request.getRequestURL());
        //请求方式
        log.info("method={}",request.getMethod());
        //类名
        log.info("class={}",joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName());
        //参数
        log.info("args={}",joinpoint.getArgs());
    }

    @After("log()")
    public  void doAfter(){
        System.out.println("222222");
    }
    @AfterReturning(returning = "object",pointcut = "log()")
    public void returning(Object object){
        log.info("response={}",object);
    }
}
