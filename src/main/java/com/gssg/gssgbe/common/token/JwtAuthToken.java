package com.gssg.gssgbe.common.token;

import static com.gssg.gssgbe.common.exception.ErrorCode.NOT_VALID_TOKEN;

import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthToken implements AuthToken<Claims> {

  private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  @Getter
  private final String token;
  private final Key secreatKey;

  JwtAuthToken(String token, Key secreatKey) {
    this.token = token;
    this.secreatKey = secreatKey;
  }

  JwtAuthToken(String email, String role, Date expiredDate, Key secreatKey) {
    Map<String, Object> claims = new HashMap<>() {
      {
        put("email", email);
        put("role", role);
      }
    };
    this.token = Jwts.builder()
        .setClaims(claims)
        .signWith(secreatKey, signatureAlgorithm)
        .setExpiration(expiredDate)
        .compact();
    this.secreatKey = secreatKey;
  }

  @Override
  public boolean validate() {
    return getClaims() != null;
  }

  @Override
  public Claims getClaims() {
    try {
      return Jwts.parserBuilder().setSigningKey(secreatKey).build().parseClaimsJws(token).getBody();
    } catch (SecurityException e) {
      log.info("### Invalid JWT signature.");
    } catch (MalformedJwtException e) {
      log.info("### Invalid JWT token.");
    } catch (ExpiredJwtException e) {
      log.info("### Expired JWT token.");
    } catch (UnsupportedJwtException e) {
      log.info("### Unsupported JWT token.");
    } catch (IllegalArgumentException e) {
      log.info("### JWT token compact of handler are invalid.");
    }

    return null;
  }

  public String getEmail() {
    return Optional.ofNullable(getClaims())
        .orElseThrow(() -> new CustomAuthenticationException(NOT_VALID_TOKEN))
        .get("email").toString();
  }
}
