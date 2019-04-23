package com.plop.springular.model;

public class SuccessResponse {
  private final int status;
  private final String message;

  public SuccessResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}