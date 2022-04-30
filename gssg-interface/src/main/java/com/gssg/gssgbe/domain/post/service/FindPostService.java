package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindPostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public List<PostDto> findAll(final Member loginMember, final NoOffsetPageRequest pageRequest) {
        return postRepository.findAll(pageRequest).stream()
            .map(post -> PostDto.of(post, post.isLike(loginMember)))
            .collect(Collectors.toList());
    }

    public List<PostDto> findByMember(final Member loginMember,
        final NoOffsetPageRequest pageRequest) {
        return postRepository.findAllByMember(loginMember, pageRequest).stream()
            .map(PostDto::of)
            .collect(Collectors.toList());
    }

    public List<PostDto> findByNickname(final String nickname,
        final NoOffsetPageRequest pageRequest) {
        final Member foundMember = memberRepository.findByNickname(nickname)
            .orElseThrow(() -> new CustomAuthenticationException(ErrorCode.NOT_EXISTS_MEMBER));
        return findByMember(foundMember, pageRequest);
    }
}
