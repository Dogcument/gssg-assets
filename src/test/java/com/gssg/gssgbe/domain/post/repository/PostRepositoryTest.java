package com.gssg.gssgbe.domain.post.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("[repo] 글")
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void afterEach() {
        postRepository.deleteAll();
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
        memberRepository.save(member);
        Subject subject = new Subject("강남역", "2호선");
        subjectRepository.save(subject);
        Post post = new Post(member, subject, "content1");

        // when
        postRepository.save(post);

        // then
        assertThat(post.getId()).isNotNull();
        assertThat(post.getWriter()).isEqualTo(member);
        assertThat(post.getCreatedAt()).isNotNull();
    }
}
