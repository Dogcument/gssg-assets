package com.gssg.gssgbe.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.user.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("[repo] 회원")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private UserRepository userRepository;

  @DisplayName("[성공] 생성")
  @Test
  public void success_create() {
    // given
    String loginId = "dan@gmail.com";
    String password = "qwerasdf";

    // when
    User user = new User(loginId, password);
    userRepository.save(user);

    // then
    em.clear();

    user = userRepository.findByLoginId(loginId).get();
    assertThat(user.getId()).isNotNull();
    assertThat(user.getPassword()).isEqualTo(password);
  }
}
