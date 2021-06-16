package com.gssg.gssgbe.config.security;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean {

  private static final String AUTHORIZATION_HEADER = "authorization";

  private final JwtAuthTokenProvider jwtAuthTokenProvider;

  JWTFilter(JwtAuthTokenProvider jwtAuthTokenProvider) {
    this.jwtAuthTokenProvider = jwtAuthTokenProvider;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    Optional.ofNullable(((HttpServletRequest) servletRequest).getHeader(AUTHORIZATION_HEADER))
        .map(authorization -> authorization.split("bearer")[1])
        .ifPresent(this::setAuthenticationToSecurityContext);

    filterChain.doFilter(servletRequest, servletResponse);
  }

  /**
   * 인증에 성공하면 Spring 이 관리하는 SecurityContext 에 인증 객체를 설정합니다.
   *
   * @param authToken
   */
  private void setAuthenticationToSecurityContext(String authToken) {
    JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(authToken);

    if (jwtAuthToken.validate()) {
      Authentication authentication = jwtAuthTokenProvider.getAuthentication(jwtAuthToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
  }
}
