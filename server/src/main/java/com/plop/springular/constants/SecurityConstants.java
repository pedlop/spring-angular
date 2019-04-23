package com.plop.springular.constants;

public class SecurityConstants {
  public static final String SECRET = "mysupersecretkey";
  public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String HEADER_STRING = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String CREATE_USER_URL = "/users/create";
}