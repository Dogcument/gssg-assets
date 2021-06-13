package com.gssg.gssgbe.domain.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("[service] 회원 로그인")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LoginMemberServiceTest {

  @Autowired
  private LoginMemberService loginMemberService;

  @MockBean
  private MemberRepository memberRepository;

  public static Stream<Member> VALID_MEMBER() {
    return TestData.VALID_MEMBER();
  }

  @DisplayName("[성공]")
  @ParameterizedTest
  @MethodSource("VALID_MEMBER")
  public void success_login(Member member) {
    // given
    given(memberRepository.findByEmail(any()))
        .willReturn(Optional.of(member));

    // when
    loginMemberService.login(member.getEmail(), member.getPassword());

    // then

  }

  public static Stream<Member> NOT_VALID_MEMBER() {
    return TestData.NOT_VALID_MEMBER();
  }

  @DisplayName("[실패] 존재하지 않는 회원")
  @ParameterizedTest
  @MethodSource("NOT_VALID_MEMBER")
  public void fail_login_notExist(Member member) {
    // given
    given(memberRepository.findByEmail(any()))
        .willReturn(Optional.empty());

    // when
    BusinessException exception = assertThrows(BusinessException.class,
        () -> loginMemberService.login(member.getEmail(), member.getPassword()));

    // then
    assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND);
  }
}
