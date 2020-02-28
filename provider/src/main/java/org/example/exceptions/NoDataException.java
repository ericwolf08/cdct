package org.example.exceptions;

public class NoDataException extends RuntimeException {
  public NoDataException() {
    super("No Data");
  }
}
