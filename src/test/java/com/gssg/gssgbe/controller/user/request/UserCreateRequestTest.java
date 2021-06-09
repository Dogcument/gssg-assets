package com.gssg.gssgbe.controller.user.request;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.user.entity.User;
import java.util.Set;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("[dto] 회원 가입 request")
class UserCreateRequestTest {

  private static final ValidatorFactory factory;
  private static final Validator validator;

  static {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  public static Stream<User> VALID_USER() {
    return TestData.VALID_USER();
  }

  @ParameterizedTest
  @MethodSource("VALID_USER")
  public void success(User user) {
    // given
    UserCreateRequest userCreateRequest = new UserCreateRequest(user.getLoginId(), user.getPassword(), user.getNickName());

    // when
    Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);

    // then
    assertThat(violations).isEmpty();
  }

  public static Stream<User> NOT_VALID_USER() {
    return TestData.NOT_VALID_USER();
  }

  @ParameterizedTest
  @MethodSource("NOT_VALID_USER")
  public void fail(User user) {
    // given
    UserCreateRequest userCreateRequest = new UserCreateRequest(user.getLoginId(), user.getPassword(), user.getNickName());

    // when
    Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);

    // then
    assertThat(violations).isNotEmpty();
    violations.forEach(v -> {
      System.out.println("### invalidValue=" + v.getInvalidValue());
      System.err.println(v.getMessage());
    });
  }
}
