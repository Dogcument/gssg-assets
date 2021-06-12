package com.gssg.gssgbe.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.user.entity.User;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[repo] 회원")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private UserRepository userRepository;

  public static Stream<User> VALID_USER() {
    return TestData.VALID_USER();
  }

  @DisplayName("[성공] 생성")
  @ParameterizedTest
  @MethodSource("VALID_USER")
  public void success_create(User newUser) {
    // given

    // when
    userRepository.save(newUser);

    // then
    em.clear();

    User createdUser = userRepository.findByLoginId(newUser.getLoginId()).get();
    assertThat(createdUser.getId()).isNotNull();
    assertThat(createdUser.getPassword()).isEqualTo(newUser.getPassword());
    assertThat(createdUser.getCreatedAt()).isNotNull();
  }
}
