package com.wiki.exceptions;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Exceptions handler.
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

  @Override
  protected final ResponseEntity<Object>
       handleMethodArgumentNotValid(
               final MethodArgumentNotValidException ex,
               final HttpHeaders headers,
               final HttpStatusCode status, final WebRequest request) {
    log.error("error method {}", ex.getMessage());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),
            status.toString(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }


  @Override
  protected final ResponseEntity<Object> handleHttpRequestMethodNotSupported(
          final HttpRequestMethodNotSupportedException ex,
          final @NonNull HttpHeaders headers,
          final @NonNull HttpStatusCode status,
          final WebRequest request) {
    log.error("error request {}", ex.getStatusCode());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            ex.getStackTrace()[0].getMethodName());
    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  protected final ResponseEntity<Object> handleAllExceptions(
          final Exception ex) {
    log.error("error exception {}", ex.getMessage());
    ErrorMessage errorMessage = new ErrorMessage(ex.getLocalizedMessage(),
            "500", ex.getStackTrace()[0].getMethodName());
    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected final ResponseEntity<Object> handleHttpMessageNotReadable(
          final HttpMessageNotReadableException ex, final HttpHeaders headers,
          final HttpStatusCode status, final WebRequest request) {
    log.error("error not readable {}", ex.getMessage());
    ErrorMessage msg = new ErrorMessage(
            ex.getLocalizedMessage(),
            HttpStatus.BAD_REQUEST.toString(),
            request.toString());
    return new ResponseEntity<>(msg, status);
  }

}
