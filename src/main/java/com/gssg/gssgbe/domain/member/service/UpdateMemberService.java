package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.request.UpdateMemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UpdateMemberService {

    private final MemberRepository memberRepository;

    public Long update(Long memberId, UpdateMemberDto dto) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        member.updateNickname(dto.getNickName());
        member.updateIntroduce(dto.getIntroduce());
        member.updateProfileDog(dto.getProfileDogType());

        return member.getId();
    }

    public Long updatePassword(Long memberId, String passsword) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        member.updatePassword(passsword);

        return member.getId();
    }
}
