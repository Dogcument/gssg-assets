package com.gssg.gssgbe.web.auth.response;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshResponse {

  @Schema(description = "JWT")
  private String jwt;

  public RefreshResponse(JwtAuthToken jwtAuthToken) {
    this.jwt = jwtAuthToken.getToken();
  }
}
