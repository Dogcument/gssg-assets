package com.gssg.gssgbe.common.token;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class RefreshTokenProvider implements AuthTokenProvider<RefreshToken> {

	private final static long REFRESH_TOKEN_RETENTION_MINUTES = 60 * 24 * 14;
	private final Key secreatKey;

	public RefreshTokenProvider(final String secreat) {
		final byte[] keyBytes = Decoders.BASE64.decode(secreat);
		this.secreatKey = Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public RefreshToken createAuthToken(final String email, final String role, final Date expiredDate) {
		return new RefreshToken(email, role, expiredDate, secreatKey);
	}

	@Override
	public RefreshToken createAuthToken(final String email, final String role) {
		final Date expiredDate = Date.from(
			LocalDateTime.now()
				.plusMinutes(REFRESH_TOKEN_RETENTION_MINUTES)
				.atZone(ZoneId.systemDefault())
				.toInstant());
		return new RefreshToken(email, role, expiredDate, secreatKey);
	}

	@Override
	public RefreshToken convertAuthToken(final String token) {
		return new RefreshToken(token, secreatKey);
	}

	public RefreshToken convertAuthToken(final JwtAuthToken jwtAuthToken) {
		final Date expiredDate = Date.from(
			LocalDateTime.now()
				.plusMinutes(REFRESH_TOKEN_RETENTION_MINUTES)
				.atZone(ZoneId.systemDefault())
				.toInstant());
		return createAuthToken(jwtAuthToken.getSubject(), jwtAuthToken.getRole(), expiredDate);
	}
}
