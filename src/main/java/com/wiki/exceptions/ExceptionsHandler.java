package com.wiki.exceptions;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Exceptions handler.
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({HttpClientErrorException.class,
                     MissingRequestValueException.class, BadRequestException.class})
  public ResponseEntity<Object> handleClientErrorException(HttpClientErrorException ex) {
    log.error("error {}", ex.getStatusText());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), "400", ex.getStatusText());
    return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(400));
  }

  @Override
  protected ResponseEntity<Object>
      handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                               HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    log.error("error {}", ex.getMessage());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),
            status.toString(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }


  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
          final HttpRequestMethodNotSupportedException ex,
          final @NonNull HttpHeaders headers,
          final @NonNull HttpStatusCode status,
          final WebRequest request) {
    log.error("error {}", ex.getStatusCode());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),
            "500", ex.getStackTrace()[0].getMethodName());
    return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf((500)));
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllExceptions(final Exception ex) {
    log.error("error {}", ex.getMessage());
    ErrorMessage errorMessage = new ErrorMessage(ex.getLocalizedMessage(),
            "500", ex.getStackTrace()[0].getMethodName());
    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
          final HttpMessageNotReadableException ex, final HttpHeaders headers,
          final HttpStatusCode status, final WebRequest request) {
    log.error("error {}", ex.getMessage());
    ErrorMessage msg = new ErrorMessage(ex.getLocalizedMessage(), "400", request.toString());
    return new ResponseEntity<>(msg, status);
  }

}
