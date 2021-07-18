package com.gssg.gssgbe.domain.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.dto.request.CreateMemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("[service] 회원 조회")
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FindMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private FindMemberService findMemberService;

    public static Stream<Member> VALID_MEMBER() {
        return TestData.VALID_MEMBER();
    }

    @DisplayName("[성공] 존재하는 이메일")
    @ParameterizedTest
    @MethodSource("VALID_MEMBER")
    public void existsEmail(Member member) {
        // given
        CreateMemberDto createDto = new CreateMemberDto(member.getEmail(), member.getPassword());
        createMemberService.create(createDto);

        // when
        boolean result = findMemberService.existsEmail(member.getEmail());

        // then
        assertThat(result).isTrue();
    }
}
