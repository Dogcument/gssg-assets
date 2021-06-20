package com.gssg.gssgbe.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@RequiredArgsConstructor
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  private final HandlerExceptionResolver handlerExceptionResolver;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) {
    handlerExceptionResolver.resolveException(request, response, null, ex);
  }
}
