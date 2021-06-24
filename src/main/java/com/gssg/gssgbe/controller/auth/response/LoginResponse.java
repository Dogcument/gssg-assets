package com.gssg.gssgbe.controller.auth.response;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

  private String token;

  public LoginResponse(JwtAuthToken jwtAuthToken) {
    token = jwtAuthToken.getToken();
  }
}
