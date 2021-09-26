package com.gssg.gssgbe.domain.member.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;

@DisplayName("[repo] 회원")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@AfterEach
	public void afterEach() {
		memberRepository.deleteAll();
	}

	static Stream<Member> VALID_MEMBER() {
		return TestData.VALID_MEMBER();
	}

	@DisplayName("[성공] 생성")
	@ParameterizedTest
	@MethodSource("VALID_MEMBER")
	void success_create(final Member member) {
		// given

		// when
		memberRepository.save(member);

		// then
		final Member createdMember = memberRepository.findByEmail(member.getEmail()).get();
		assertThat(createdMember.getId()).isNotNull();
		System.out.println("### member.getPassword()=" + member.getPassword()
			+ ", createdMember.getPassword()=" + createdMember.getPassword());
		assertThat(createdMember.getCreatedAt()).isNotNull();
	}

	@DisplayName("[성공] email 존재 여부")
	@ParameterizedTest
	@MethodSource("VALID_MEMBER")
	void success_exists(final Member member) {
		// given
		memberRepository.save(member);

		// when
		assertThat(memberRepository.existsByEmail(member.getEmail())).isTrue();

		// then
	}
}
