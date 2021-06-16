package com.gssg.gssgbe.config;

import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

  @Value("${application.jwt.secreat}")
  private String secreat;

  @Bean
  public JwtAuthTokenProvider jwtAuthTokenProvider() {
    return new JwtAuthTokenProvider(secreat);
  }
}
