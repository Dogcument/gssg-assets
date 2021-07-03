package com.gssg.gssgbe.web.auth.response;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.RefreshToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshResponse {

  @Schema(description = "JWT")
  private String jwt;

  @Schema(description = "Refresh Token")
  private String refreshToken;

  public RefreshResponse(JwtAuthToken jwtAuthToken, RefreshToken refreshToken) {
    this.jwt = jwtAuthToken.getToken();
    this.refreshToken = refreshToken.getToken();
  }
}
