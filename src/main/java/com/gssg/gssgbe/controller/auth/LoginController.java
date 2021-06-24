package com.gssg.gssgbe.controller.auth;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.RefreshToken;
import com.gssg.gssgbe.common.token.RefreshTokenProvider;
import com.gssg.gssgbe.controller.auth.request.RefreshRequest;
import com.gssg.gssgbe.controller.auth.response.LoginResponse;
import com.gssg.gssgbe.controller.auth.response.RefreshResponse;
import com.gssg.gssgbe.domain.auth.service.LoginService;
import com.gssg.gssgbe.domain.member.dto.request.LoginMemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증")
@RequiredArgsConstructor
@RestController
public class LoginController {

  private final RefreshTokenProvider refreshTokenProvider;
  private final LoginService loginService;

  @Operation(summary = "로그인")
  @PostMapping("/api/v1/login")
  public LoginResponse login(@RequestBody @Valid LoginMemberRequest request) {
    JwtAuthToken jwtAuthToken = loginService.login(request.getLoginId(), request.getPassword());
    RefreshToken refreshToken = loginService.createRefreshToken(jwtAuthToken);

    return new LoginResponse(jwtAuthToken, refreshToken);
  }

  @Operation(summary = "JWT 재발급")
  @PostMapping("/api/v1/login/refresh")
  public RefreshResponse refresh(@RequestBody @Valid RefreshRequest request) {
    RefreshToken refreshToken = refreshTokenProvider.convertAuthToken(request.getRefreshToken());
    JwtAuthToken jwtAuthToken = loginService.createJwtAuthToken(refreshToken);

    return new RefreshResponse(jwtAuthToken);
  }
}
