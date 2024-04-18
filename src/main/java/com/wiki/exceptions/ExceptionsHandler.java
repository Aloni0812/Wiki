package com.wiki.exceptions;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Exceptions handler.
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

  /**
   * Handle client error exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler({HttpClientErrorException.class, MissingRequestValueException.class})
  public ResponseEntity<Object> handleClientErrorException(HttpClientErrorException ex) {
    log.error("error {}", ex.getStatusText());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(400));
  }

  /**
   * Handle server error exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler({HttpServerErrorException.class})
  public ResponseEntity<Object> handleServerErrorException(HttpServerErrorException ex) {
    log.error("error {}", ex.getStatusText());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf((500)));
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
          final HttpRequestMethodNotSupportedException ex,
          final @NonNull HttpHeaders headers,
          final @NonNull HttpStatusCode status,
          final WebRequest request) {
    log.error("error {}", ex.getStatusCode());
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf((500)));
  }

  /**
   * Handle all exceptions response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllExceptions(final Exception ex) {
    log.error("error {}", ex.getMessage());
    return new ResponseEntity<>("Error 500 " + ex.getLocalizedMessage().toString(),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
