package com.wiki.service;

import com.wiki.controller.WikiController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Aspect

public final class CounterService {
  static final Logger LOGGER = LogManager.getLogger(WikiController.class);
  private CounterService() {
  }
  private AtomicInteger requestCount = new AtomicInteger(0);
  @Pointcut("within(com.wiki.controller..*)")
  public void controllerMethods() { }

@Before("controllerMethods()")
  public synchronized void incrementRequestCount() {
    int i = requestCount.incrementAndGet();
    LOGGER.info("Текущее количесво вызовов {}", i);
    System.out.println("Counter value is"+i);
  }
}
