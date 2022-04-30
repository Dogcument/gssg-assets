package com.gssg.gssgbe.common.token;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
            .map(this::createSpringSecurityUser)
            .orElseThrow(() -> new CustomAuthenticationException(ErrorCode.NOT_EXISTS_MEMBER));
    }

    private User createSpringSecurityUser(final Member member) {
        final List<GrantedAuthority> grantedAuthorities = Collections.singletonList(
            new SimpleGrantedAuthority(member.getRole()));
        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }
}
