package com.gssg.gssgbe.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.user.entity.User;
import com.gssg.gssgbe.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("[service] 회원 로그인")
@SpringBootTest
class UserLoginServiceTest {

  @Autowired
  private UserLoginService userLoginService;

  @MockBean
  private UserRepository userRepository;

  @DisplayName("[성공]")
  @Test
  public void success_login() {
    // given
    User user = new User("test@gmail.com", "1234");
    given(userRepository.findByLoginId(any()))
        .willReturn(Optional.of(user));

    // when
    userLoginService.login(user.getLoginId(), user.getPassword());

    // then

  }

  @DisplayName("[실패] 존재하지 않는 회원")
  @Test
  public void fail_login_notExist() {
    // given
    User user = new User("test@gmail.com", "1234");
    given(userRepository.findByLoginId(any()))
        .willReturn(Optional.empty());

    // when
    BusinessException exception = assertThrows(BusinessException.class,
        () -> userLoginService.login(user.getLoginId(), user.getPassword()));

    // then
    assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND);
  }
}
