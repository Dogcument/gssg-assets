package com.gssg.gssgbe.common.token;

import java.util.Date;
import org.springframework.security.core.Authentication;

public interface AuthTokenProvider<T> {

  T createAuthToken(String email, String role, Date expiredDate);

  T convertAuthToken(String token);

  Authentication getAuthentication(T authToken);
}
