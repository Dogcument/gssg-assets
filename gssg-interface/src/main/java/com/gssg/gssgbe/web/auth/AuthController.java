package com.gssg.gssgbe.web.auth;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import com.gssg.gssgbe.common.token.RefreshToken;
import com.gssg.gssgbe.common.token.RefreshTokenProvider;
import com.gssg.gssgbe.domain.auth.service.LoginService;
import com.gssg.gssgbe.web.auth.request.LoginRequest;
import com.gssg.gssgbe.web.auth.request.RefreshRequest;
import com.gssg.gssgbe.web.auth.response.LoginResponse;
import com.gssg.gssgbe.web.auth.response.RefreshResponse;
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
public class AuthController {

    private final RefreshTokenProvider refreshTokenProvider;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final LoginService loginService;

    @Operation(summary = "로그인")
    @PostMapping("/api/v1/auth/login")
    public LoginResponse login(@RequestBody @Valid final LoginRequest request) {
        final JwtAuthToken jwtAuthToken = loginService.login(
                request.getLoginId(),
                request.getPassword()
        );
        final RefreshToken refreshToken = refreshTokenProvider.convertAuthToken(jwtAuthToken);

        return new LoginResponse(jwtAuthToken, refreshToken);
    }

    @Operation(summary = "JWT 재발급")
    @PostMapping("/api/v1/auth/refresh")
    public RefreshResponse refresh(@RequestBody @Valid final RefreshRequest request) {
        final RefreshToken refreshToken = refreshTokenProvider.convertAuthToken(
            request.getRefreshToken());
        final JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(refreshToken);
        final RefreshToken newRefreshToken = refreshTokenProvider.convertAuthToken(jwtAuthToken);

        return new RefreshResponse(jwtAuthToken, newRefreshToken);
    }
}
