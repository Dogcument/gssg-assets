package com.gssg.gssgbe.web.member.request;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
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
class CreateMemberRequestTest {

  private static final ValidatorFactory factory;
  private static final Validator validator;

  static {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  public static Stream<Member> VALID_MEMBER() {
    return TestData.VALID_MEMBER();
  }

  @ParameterizedTest
  @MethodSource("VALID_MEMBER")
  public void success(Member member) {
    // given
    CreateMemberRequest reqeust =
        new CreateMemberRequest(member.getEmail(), member.getPassword(), member.getNickName(), ProfileDogType.DALMATIAN);

    // when
    Set<ConstraintViolation<CreateMemberRequest>> violations = validator.validate(reqeust);

    // then
    assertThat(violations).isEmpty();
  }

  public static Stream<Member> NOT_VALID_MEMBER() {
    return TestData.NOT_VALID_MEMBER();
  }

  @ParameterizedTest
  @MethodSource("NOT_VALID_MEMBER")
  public void fail(Member member) {
    // given
    CreateMemberRequest request =
        new CreateMemberRequest(member.getEmail(), member.getPassword(), member.getNickName(), ProfileDogType.DALMATIAN);

    // when
    Set<ConstraintViolation<CreateMemberRequest>> violations = validator.validate(request);

    // then
    assertThat(violations).isNotEmpty();
    violations.forEach(v -> {
      System.out.println("### invalidValue=" + v.getInvalidValue());
      System.err.println(v.getMessage());
    });
  }
}
