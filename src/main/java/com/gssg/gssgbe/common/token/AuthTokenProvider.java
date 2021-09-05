package com.gssg.gssgbe.common.token;

import java.util.Date;

public interface AuthTokenProvider<T> {

	T createAuthToken(String email, String role, Date expiredDate);

	T createAuthToken(String email, String role);

	T convertAuthToken(String token);
}
