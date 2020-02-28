package org.example.exceptions;

public class InvalidQueryParameterException extends RuntimeException {
  public InvalidQueryParameterException(String message, Exception cause) {
    super(message, cause);
  }
}
