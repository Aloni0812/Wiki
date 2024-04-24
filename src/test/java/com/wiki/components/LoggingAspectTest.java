package com.wiki.components;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class LoggingAspectTest {
  @Mock
  private JoinPoint joinPoint;

  @Mock
  private Signature signature;

  private LoggingAspect loggingAspect;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    loggingAspect = new LoggingAspect();
  }

  @Test
  void testAfterLogServiceMethods() {
    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.getName()).thenReturn("someMethod");

    loggingAspect.afterLogServiceMethods(joinPoint);
  }

  @Test
  void testBeforeLogCacheMethods() {
    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.getName()).thenReturn("cacheMethod");

    loggingAspect.beforeLogCacheMethods(joinPoint);
  }

  @Test
  void testAfterLogCacheMethods() {
    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.getName()).thenReturn("cacheMethod");

    loggingAspect.afterLogCacheMethods(joinPoint);
  }
}