package com.gssg.gssgbe.domain.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;

@DisplayName("[repo] 회원")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MemberRepositoryTest {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

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
    em.clear();

    Member createdMember = memberRepository.findByEmail(member.getEmail()).get();
    assertThat(createdMember.getId()).isNotNull();
    assertThat(passwordEncoder.matches(member.getPassword(), createdMember.getPassword())).isTrue();
    assertThat(createdMember.getCreatedAt()).isNotNull();
  }
}
