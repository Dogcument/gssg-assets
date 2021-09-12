package com.gssg.gssgbe.common.resolver;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String AUTHORIZATION_HEADER = "authorization";

	private final JwtAuthTokenProvider jwtAuthTokenProvider;
	private final MemberRepository memberRepository;

	@Override
	public boolean supportsParameter(final MethodParameter methodParameter) {
		return methodParameter.hasParameterAnnotation(LoginMember.class);
	}

	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
		final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
		final LoginMember loginUserAnnotation = parameter.getParameterAnnotation(LoginMember.class);
		if (!loginUserAnnotation.required()) {
			return null;
		}

		final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		return Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER))
			.map(authorization -> authorization.split("bearer")[1])
			.map(jwtAuthTokenProvider::convertAuthToken)
			.map(JwtAuthToken::getSubject)
			.map(email -> memberRepository.findByEmail(email)
				.orElseThrow(() -> new CustomAuthrizationException(ErrorCode.NOT_EXISTS_MEMBER)))
			.orElseThrow(() -> new CustomAuthrizationException(ErrorCode.FORBIDDEN));
	}
}
