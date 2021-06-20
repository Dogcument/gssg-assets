package com.gssg.gssgbe.common.token;

public interface AuthToken<T> {

  boolean validate();

  T getClaims();
}
