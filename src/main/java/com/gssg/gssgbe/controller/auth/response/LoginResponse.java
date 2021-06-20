package com.gssg.gssgbe.controller.auth.response;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import lombok.Getter;

@Getter
public class LoginResponse {

  private final String token;

  public LoginResponse(JwtAuthToken jwtAuthToken) {
    token = jwtAuthToken.getToken();
  }
}
