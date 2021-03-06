package com.gssg.gssgbe.common.token;

import static com.gssg.gssgbe.common.exception.ErrorCode.NOT_VALID_TOKEN;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Slf4j
public class RefreshToken implements AuthToken<Claims> {

    private static final String AUTHORITIES_KEY = "role";
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Getter
    private final String token;
    private final Key secreatKey;

    RefreshToken(final String token, final Key secreatKey) {
        this.token = token;
        this.secreatKey = secreatKey;
    }

    RefreshToken(final String email, final String role, final Date expiredDate,
        final Key secreatKey) {
        final Map<String, Object> claims = new HashMap<>() {
            {
                put(AUTHORITIES_KEY, role);
            }
        };

        this.token = Jwts.builder()
            .setClaims(claims)
            .setSubject(email)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(expiredDate)
            .signWith(secreatKey, signatureAlgorithm)
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
            return Jwts.parserBuilder().setSigningKey(secreatKey).build().parseClaimsJws(token)
                .getBody();
        } catch (final SecurityException e) {
            log.info("### Invalid RefreshToken signature.");
        } catch (final MalformedJwtException e) {
            log.info("### Invalid RefreshToken.");
        } catch (final ExpiredJwtException e) {
            log.info("### Expired RefreshToken.");
        } catch (final UnsupportedJwtException e) {
            log.info("### Unsupported RefreshToken.");
        } catch (final IllegalArgumentException e) {
            log.info("### RefreshToken compact of handler are invalid.");
        }

        return null;
    }

    @Override
    public Authentication getAuthentication() {
        if (this.validate()) {
            final Claims claims = this.getClaims();
            final Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority(claims.get(AUTHORITIES_KEY).toString()));
            final User principal = new User(claims.getSubject(), "", authorities);

            return new UsernamePasswordAuthenticationToken(principal, this, authorities);
        } else {
            throw new CustomAuthenticationException(ErrorCode.FAILED_GENERATE_TOKEN);
        }
    }

    public String getSubject() {
        return Optional.ofNullable(getClaims())
            .orElseThrow(() -> new CustomAuthenticationException(NOT_VALID_TOKEN))
            .get("sub").toString();
    }

    public String getRole() {
        return Optional.ofNullable(getClaims())
            .orElseThrow(() -> new CustomAuthenticationException(NOT_VALID_TOKEN))
            .get(AUTHORITIES_KEY).toString();
    }
}
