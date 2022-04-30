package com.gssg.gssgbe.domain.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.dto.request.CreateMemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[service] 회원 가입")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CreateMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private MemberRepository memberRepository;

    public static Stream<Member> VALID_MEMBER() {
        return TestData.VALID_MEMBER();
    }

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @DisplayName("[성공]")
    @ParameterizedTest
    @MethodSource("VALID_MEMBER")
    void success(final Member member) {
        // given
        final CreateMemberDto createDto = new CreateMemberDto(
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getProfileDog(),
                member.getIntroduce());

        // when
        final long memberId = createMemberService.create(createDto);

        // then
        final Member createdMember = memberRepository.findById(memberId).get();
        assertThat(createdMember.getEmail()).isNotNull();
    }

    @DisplayName("[실패] 이미 가입된 이메일")
    @ParameterizedTest
    @MethodSource("VALID_MEMBER")
    void failed_existsEmail(final Member member) {
        // given
        final CreateMemberDto createDto = new CreateMemberDto(
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getProfileDog(),
                member.getIntroduce());
        createMemberService.create(createDto);

        // when
        final BusinessException exception = assertThrows(BusinessException.class,
                () -> createMemberService.create(createDto));

        // then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EXISTS_EMAIL);
    }
}
