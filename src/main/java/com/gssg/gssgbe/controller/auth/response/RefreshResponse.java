package com.gssg.gssgbe.controller.auth.response;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshResponse {

  private String jwt;

  public RefreshResponse(JwtAuthToken jwtAuthToken) {
    this.jwt = jwtAuthToken.getToken();
  }
}
