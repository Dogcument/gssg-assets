package com.gssg.gssgbe.common.token;

import org.springframework.security.core.Authentication;

public interface AuthToken<T> {

  boolean validate();

  T getClaims();

  Authentication getAuthentication();
}
