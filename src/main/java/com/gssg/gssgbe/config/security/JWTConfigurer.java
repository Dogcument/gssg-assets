package com.gssg.gssgbe.config.security;

import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final JwtAuthTokenProvider jwtAuthTokenProvider;

  public JWTConfigurer(JwtAuthTokenProvider jwtAuthTokenProvider) {
    this.jwtAuthTokenProvider = jwtAuthTokenProvider;
  }

  @Override
  public void configure(HttpSecurity http) {
    JWTFilter customFilter = new JWTFilter(jwtAuthTokenProvider);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
