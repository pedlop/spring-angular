package com.plop.springular.model;

public class Token {
  private String access;
  private String refresh;

  public Token(String access, String refresh) {
    this.access = access;
    this.refresh = refresh;
  }
}