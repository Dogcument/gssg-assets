package com.gssg.gssgbe.domain.auth.service;

import static com.gssg.gssgbe.common.exception.ErrorCode.NOT_EXIST_AUTHORIZATION;
import static com.gssg.gssgbe.common.exception.ErrorCode.NOT_EXIST_MEMBER;
import static com.gssg.gssgbe.common.exception.ErrorCode.NOT_VALID_PASSWORD;

import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import com.gssg.gssgbe.common.token.RefreshToken;
import com.gssg.gssgbe.common.token.RefreshTokenProvider;
import com.gssg.gssgbe.common.token.Role;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

  private final static long JWT_RETENTION_MINUTES = 60 * 3;
  private final static long REFRESH_TOKEN_RETENTION_MINUTES = 60 * 24 * 14;

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtAuthTokenProvider jwtAuthTokenProvider;
  private final RefreshTokenProvider refreshTokenProvider;
  private final PasswordEncoder passwordEncoder;

  private final MemberRepository memberRepository;

  public JwtAuthToken login(String loginId, String password) {
    Member member = memberRepository.findByEmail(loginId)
        .orElseThrow(() -> new CustomAuthenticationException(NOT_EXIST_MEMBER));

    if (!passwordEncoder.matches(password, member.getPassword())) {
      throw new CustomAuthenticationException(NOT_VALID_PASSWORD);
    }

    Authentication authentication = getAuthentication(password, member);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return getJwtAuthToken(member, authentication);
  }

  public RefreshToken createRefreshToken(JwtAuthToken jwtAuthToken) {
    Date expiredDate = Date.from(
        LocalDateTime.now().plusMinutes(REFRESH_TOKEN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());

    return refreshTokenProvider.createAuthToken(jwtAuthToken.getSubject(), jwtAuthToken.getRole(), expiredDate);
  }

  public JwtAuthToken createJwtAuthToken(RefreshToken refreshToken) {
    Date expiredDate = Date.from(
        LocalDateTime.now().plusMinutes(JWT_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());

    return jwtAuthTokenProvider.createAuthToken(refreshToken.getSubject(), refreshToken.getRole(), expiredDate);
  }

  private JwtAuthToken getJwtAuthToken(Member member, Authentication authentication) {
    Role role = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .findFirst()
        .map(Role::of)
        .orElseThrow(() -> new CustomAuthrizationException(NOT_EXIST_AUTHORIZATION));
    Date expiredDate = Date.from(
        LocalDateTime.now().plusMinutes(JWT_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());

    return jwtAuthTokenProvider.createAuthToken(member.getEmail(), role.getCode(), expiredDate);
  }

  private Authentication getAuthentication(String password, Member member) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), password);
    return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
  }
}
