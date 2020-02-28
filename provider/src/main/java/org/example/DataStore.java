package org.example;

public class DataStore {
  public static final DataStore INSTANCE = new DataStore();
  private int dataCount = 1000;

  private DataStore() { }

  public int getDataCount() {
    return dataCount;
  }

  public void setDataCount(int dataCount) {
    this.dataCount = dataCount;
  }
}
