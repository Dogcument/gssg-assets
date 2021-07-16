package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindMemberService {

    private final MemberRepository memberRepository;

    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream()
            .map(MemberDto::new)
            .collect(Collectors.toList());
    }

    public Optional<MemberDto> findById(Long memberId) {
        return memberRepository.findById(memberId)
            .map(MemberDto::new);
    }

    public boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
