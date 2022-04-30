package com.gssg.gssgbe.domain.auth.service;

import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import com.gssg.gssgbe.common.token.Role;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.gssg.gssgbe.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    public JwtAuthToken login(final String loginId, final String password) {
        final Member member = memberRepository.findByEmail(loginId)
                .orElseThrow(() -> new CustomAuthenticationException(NOT_EXISTS_MEMBER));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomAuthenticationException(NOT_VALID_PASSWORD);
        }

        final Authentication authentication = getAuthentication(member, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return createJwtAuthToken(member, authentication);
    }

    private JwtAuthToken createJwtAuthToken(final Member member, final Authentication authentication) {
        final Role role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .map(Role::of)
                .orElseThrow(() -> new CustomAuthrizationException(NOT_EXISTS_AUTHORIZATION));

        return jwtAuthTokenProvider.createAuthToken(member.getEmail(), role.getCode());
    }

    private Authentication getAuthentication(final Member member, final String password) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                member.getEmail(), password);
        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }
}
