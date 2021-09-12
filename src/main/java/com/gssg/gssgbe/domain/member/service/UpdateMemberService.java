package com.gssg.gssgbe.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.request.UpdateMemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class UpdateMemberService {

	private final MemberRepository memberRepository;

	public Long update(final Long memberId, final UpdateMemberDto dto) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

		member.updateNickname(dto.getNickname());
		member.updateIntroduce(dto.getIntroduce());
		member.updateProfileDog(dto.getProfileDogType());

		return member.getId();
	}

	public Long updatePassword(final Long memberId, final String passsword) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

		member.updatePassword(passsword);

		return member.getId();
	}
}
