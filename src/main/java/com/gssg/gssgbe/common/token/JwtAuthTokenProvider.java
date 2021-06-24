package com.gssg.gssgbe.common.token;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtAuthTokenProvider implements AuthTokenProvider<JwtAuthToken> {

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
  public JwtAuthToken convertAuthToken(String token) {
    return new JwtAuthToken(token, secreatKey);
  }
}
