package com.gssg.gssgbe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf().disable()

        // 인증 또는 인가에 실패한 경우 Exception 처리
        .exceptionHandling()

        .and()
        .headers()
        .frameOptions()
        .sameOrigin()

        // 세션 기능을 사용하지 않는다
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // 인증 여부와 관계 없이 모두 접근 가능한 API
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/members/**").permitAll()
        .antMatchers("/api/v1/login").permitAll()

        // 권한자만 접근 가능한 API
        .anyRequest().authenticated()

        .and();
//        .apply(securityConfigurerAdapter());
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .antMatchers("/", "/h2-console/**");
  }
}
