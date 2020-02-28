package org.example.exceptions;

public class QueryParameterRequiredException extends RuntimeException {
  public QueryParameterRequiredException(String message) {
    super(message);
  }
}
