package com.gssg.gssgbe.domain.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[repo] 회원")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @AfterEach
  public void afterEach() {
    memberRepository.deleteAll();
  }

  public static Stream<Member> VALID_MEMBER() {
    return TestData.VALID_MEMBER();
  }

  @DisplayName("[성공] 생성")
  @ParameterizedTest
  @MethodSource("VALID_MEMBER")
  public void success_create(Member member) {
    // given

    // when
    memberRepository.save(member);

    // then
    Member createdMember = memberRepository.findByEmail(member.getEmail()).get();
    assertThat(createdMember.getId()).isNotNull();
    System.out.println("### member.getPassword()=" + member.getPassword()
        + ", createdMember.getPassword()=" + createdMember.getPassword());
    assertThat(createdMember.getCreatedAt()).isNotNull();
  }

  @DisplayName("[성공] email 존재 여부")
  @ParameterizedTest
  @MethodSource("VALID_MEMBER")
  public void success_exists(Member member) {
    // given
    memberRepository.save(member);

    // when
    assertThat(memberRepository.existsByEmail(member.getEmail())).isTrue();

    // then
  }
}