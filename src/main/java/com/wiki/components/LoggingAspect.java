package com.wiki.components;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(* com.wiki.service.*.*(..))")
  public void serviceMethods() {
  }

  @Before("serviceMethods()")
  public void beforeLogServiceMethods(final JoinPoint joinPoint) {
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    logger.info("Service method: class: {}, method: {}", className, methodName);
  }

  @After("serviceMethods()")
  public void afterLogServiceMethods(final JoinPoint joinPoint) {
    logger.info("Method {} complete", joinPoint.getSignature().getName());
  }

  @Before("execution(* com.wiki.cache.DataCache.*(..))")
  public void beforeLogCacheMethods(final JoinPoint joinPoint) {
    logger.info("Cache method: {}", joinPoint.getSignature().getName());
  }

  @After("execution(* com.wiki.cache.DataCache.*(..))")
  public void afterLogCacheMethods(final JoinPoint joinPoint) {
    logger.info("Cache method compete: {}", joinPoint.getSignature().getName());
  }
}
