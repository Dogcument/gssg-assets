package com.gssg.gssgbe.common.token;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class RefreshTokenProvider implements AuthTokenProvider<RefreshToken> {

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
  public RefreshToken convertAuthToken(String token) {
    return new RefreshToken(token, secreatKey);
  }
}
