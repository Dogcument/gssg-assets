package com.gssg.gssgbe.common.token;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JwtAuthTokenProvider implements AuthTokenProvider<JwtAuthToken> {

  private final static long JWT_RETENTION_MINUTES = 60 * 3;
  private final Key secreatKey;

  public JwtAuthTokenProvider(String secreat) {
    byte[] keyBytes = Decoders.BASE64.decode(secreat);
    this.secreatKey = Keys.hmacShaKeyFor(keyBytes);
  }

  @Override
  public JwtAuthToken createAuthToken(String email, String role, Date expiredDate) {
    return new JwtAuthToken(email, role, expiredDate, secreatKey);
  }

  @Override
  public JwtAuthToken createAuthToken(String email, String role) {
    Date expiredDate = Date.from(
        LocalDateTime.now().plusMinutes(JWT_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
    return new JwtAuthToken(email, role, expiredDate, secreatKey);
  }

  @Override
  public JwtAuthToken convertAuthToken(String token) {
    return new JwtAuthToken(token, secreatKey);
  }

  public JwtAuthToken convertAuthToken(RefreshToken refreshToken) {
    Date expiredDate = Date.from(
        LocalDateTime.now().plusMinutes(JWT_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
    return new JwtAuthToken(refreshToken.getSubject(), refreshToken.getRole(), expiredDate, secreatKey);
  }
}
