package org.example.controller;

import org.example.exceptions.InvalidQueryParameterException;
import org.example.exceptions.NoDataException;
import org.example.exceptions.QueryParameterRequiredException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackageClasses = RootController.class)
public class RootControllerAdvice extends ResponseEntityExceptionHandler {
  @ExceptionHandler({ InvalidQueryParameterException.class, QueryParameterRequiredException.class})
  @ResponseBody
  public ResponseEntity<String> handleControllerException(HttpServletRequest request, Throwable ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>("{\"error\": \"" + ex.getMessage() + "\"}", headers, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoDataException.class)
  @ResponseBody
  ResponseEntity handleNoDataException(HttpServletRequest request, Throwable ex) {
    return new ResponseEntity(HttpStatus.NOT_FOUND);
  }
}
