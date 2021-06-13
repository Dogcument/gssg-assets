package com.gssg.gssgbe.domain.post.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.entity.Post;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[repo] 글")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostRepositoryTest {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private PostRepository postRepository;

  public static Stream<Member> VALID_MEMBER() {
    return TestData.VALID_MEMBER();
  }

  @DisplayName("[성공] 생성")
  @ParameterizedTest
  @MethodSource("VALID_MEMBER")
  public void success_create(Member member) {
    // given
    Post post = new Post(null, "content1");

    // when
    postRepository.save(post);

    // then
    assertThat(post.getId()).isNotNull();
    assertThat(post.getCreatedAt()).isNotNull();
  }
}
