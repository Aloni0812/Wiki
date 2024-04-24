package com.wiki.exceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExceptionsHandlerTest {

  private ExceptionsHandler exceptionsHandler;
  private WebRequest mockWebRequest;

  @BeforeEach
  void setup() {
    exceptionsHandler = new ExceptionsHandler();
    mockWebRequest = mock(WebRequest.class);
  }

  @Test
  void testHandleHttpRequestMethodNotSupported() {
    HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("GET");
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ResponseEntity<Object> response = exceptionsHandler.handleHttpRequestMethodNotSupported(ex, headers, status, mockWebRequest);
    assertEquals(status, response.getStatusCode());
    assertEquals(ErrorMessage.class, response.getBody().getClass());
    ErrorMessage errorMessage = (ErrorMessage) response.getBody();
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), errorMessage.getNumberError());
  }

  @Test
  void testHandleAllExceptions() {
    Exception ex = new Exception("Test exception");
    ResponseEntity<Object> response = exceptionsHandler.handleAllExceptions(ex);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals(ErrorMessage.class, response.getBody().getClass());
    ErrorMessage errorMessage = (ErrorMessage) response.getBody();
    assertEquals(ex.getLocalizedMessage(), errorMessage.getMessage());
    assertEquals("500", errorMessage.getNumberError());
  }

  @Test
  void testHandleHttpMessageNotReadable() {
    HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Message not readable");
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ResponseEntity<Object> response = exceptionsHandler.handleHttpMessageNotReadable(ex, headers, status, mockWebRequest);
    assertEquals(status, response.getStatusCode());
    assertEquals(ErrorMessage.class, response.getBody().getClass());
    ErrorMessage errorMessage = (ErrorMessage) response.getBody();
    assertEquals(ex.getLocalizedMessage(), errorMessage.getMessage());
    assertEquals(status.toString(), errorMessage.getNumberError());
  }
}
