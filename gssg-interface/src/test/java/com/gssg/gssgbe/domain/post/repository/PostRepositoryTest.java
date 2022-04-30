package com.gssg.gssgbe.domain.post.repository;

import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import com.gssg.gssgbe.util.TestMemberInit;
import com.gssg.gssgbe.util.TestSubjectInit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[repo] 글")
@Transactional
@Import({TestMemberInit.class, TestSubjectInit.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private PostRepository postRepository;

    @TestFactory
    Stream<DynamicNode> create() {
        final Subject subject = subjectRepository.findAll().get(0);

        return memberRepository.findAll().stream()
                .map(member -> DynamicTest.dynamicTest(member.getEmail() + " 가 생성", () -> {
                                    // given
                                    final Post post = Post.of(member, subject, "content1");

                                    // when
                                    postRepository.save(post);

                                    // then
                                    assertThat(post.getId()).isNotNull();
                                    assertThat(post.getMember()).isEqualTo(member);
                                    assertThat(post.getCreatedAt()).isNotNull();
                                    assertThat(post.getDeleted()).isFalse();
                                }
                        )
                );
    }
}
