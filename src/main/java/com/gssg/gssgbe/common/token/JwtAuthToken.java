package com.gssg.gssgbe.common.token;

import static com.gssg.gssgbe.common.exception.ErrorCode.*;

import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthToken implements AuthToken<Claims> {

	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	private static final String AUTHORITIES_KEY = "role";

	@Getter
	private final String token;
	private final Key secreatKey;

	JwtAuthToken(final String token, final Key secreatKey) {
		this.token = token;
		this.secreatKey = secreatKey;
	}

	JwtAuthToken(final String email, final String role, final Date expiredDate, final Key secreatKey) {
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
			return Jwts.parserBuilder().setSigningKey(secreatKey).build().parseClaimsJws(token).getBody();
		} catch (final SecurityException e) {
			log.info("### Invalid JWT signature.");
		} catch (final MalformedJwtException e) {
			log.info("### Invalid JWT token.");
		} catch (final ExpiredJwtException e) {
			log.info("### Expired JWT token.");
		} catch (final UnsupportedJwtException e) {
			log.info("### Unsupported JWT token.");
		} catch (final IllegalArgumentException e) {
			log.info("### JWT token compact of handler are invalid.");
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
