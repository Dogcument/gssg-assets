package com.gssg.gssgbe.common.token;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class RefreshTokenProvider implements AuthTokenProvider<RefreshToken> {

  private final static long REFRESH_TOKEN_RETENTION_MINUTES = 60 * 24 * 14;
  private final Key secreatKey;

  public RefreshTokenProvider(String secreat) {
    byte[] keyBytes = Decoders.BASE64.decode(secreat);
    this.secreatKey = Keys.hmacShaKeyFor(keyBytes);
  }

  @Override
  public RefreshToken createAuthToken(String email, String role, Date expiredDate) {
    return new RefreshToken(email, role, expiredDate, secreatKey);
  }

  @Override
  public RefreshToken createAuthToken(String email, String role) {
    Date expiredDate = Date.from(
        LocalDateTime.now().plusMinutes(REFRESH_TOKEN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
    return new RefreshToken(email, role, expiredDate, secreatKey);
  }

  @Override
  public RefreshToken convertAuthToken(String token) {
    return new RefreshToken(token, secreatKey);
  }

  public RefreshToken convertAuthToken(JwtAuthToken jwtAuthToken) {
    Date expiredDate = Date.from(
        LocalDateTime.now().plusMinutes(REFRESH_TOKEN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
    return createAuthToken(jwtAuthToken.getSubject(), jwtAuthToken.getRole(), expiredDate);
  }
}
