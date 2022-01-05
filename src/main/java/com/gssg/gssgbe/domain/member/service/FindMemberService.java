package com.gssg.gssgbe.domain.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindMemberService {

	private final MemberRepository memberRepository;

	public List<MemberDto> findAll() {
		return memberRepository.findAll().stream()
			.map(MemberDto::of)
			.collect(Collectors.toList());
	}

	public Optional<MemberDto> findById(final Long memberId) {
		return memberRepository.findById(memberId)
			.map(MemberDto::of);
	}

	public Optional<MemberDto> findByNickname(final String Nickname) {
		return memberRepository.findByNickname(Nickname)
				.map(MemberDto::of);
	}

	public boolean existsEmail(final String email) {
		return memberRepository.existsByEmail(email);
	}

	public boolean existsNickname(final String nickname) {
		return memberRepository.existsByNickname(nickname);
	}
}
