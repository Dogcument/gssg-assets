package com.gssg.gssgbe.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.user.entity.User;
import com.gssg.gssgbe.user.repository.UserRepository;
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
class UserLoginServiceTest {

  @Autowired
  private UserLoginService userLoginService;

  @MockBean
  private UserRepository userRepository;

  public static Stream<User> VALID_USER() {
    return TestData.VALID_USER();
  }

  @DisplayName("[성공]")
  @ParameterizedTest
  @MethodSource("VALID_USER")
  public void success_login(User user) {
    // given
    given(userRepository.findByLoginId(any()))
        .willReturn(Optional.of(user));

    // when
    userLoginService.login(user.getLoginId(), user.getPassword());

    // then

  }

  public static Stream<User> NOT_VALID_USER() {
    return TestData.NOT_VALID_USER();
  }

  @DisplayName("[실패] 존재하지 않는 회원")
  @ParameterizedTest
  @MethodSource("NOT_VALID_USER")
  public void fail_login_notExist(User user) {
    // given
    given(userRepository.findByLoginId(any()))
        .willReturn(Optional.empty());

    // when
    BusinessException exception = assertThrows(BusinessException.class,
        () -> userLoginService.login(user.getLoginId(), user.getPassword()));

    // then
    assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND);
  }
}
