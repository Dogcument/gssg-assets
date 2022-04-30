package com.gssg.gssgbe.web.member.request;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    public static Stream<Member> NOT_VALID_MEMBER() {
        return TestData.NOT_VALID_MEMBER();
    }

    @ParameterizedTest
    @MethodSource("VALID_MEMBER")
    void success(final Member member) {
        // given
        final CreateMemberRequest reqeust = new CreateMemberRequest(
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getProfileDog(),
                member.getIntroduce());

        // when
        final Set<ConstraintViolation<CreateMemberRequest>> violations = validator.validate(reqeust);

        // then
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("NOT_VALID_MEMBER")
    void fail(final Member member) {
        // given
        final CreateMemberRequest reqeust = new CreateMemberRequest(
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getProfileDog(),
                member.getIntroduce());

        // when
        final Set<ConstraintViolation<CreateMemberRequest>> violations = validator.validate(reqeust);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(v -> {
            System.out.println("### invalidValue=" + v.getInvalidValue());
            System.err.println(v.getMessage());
        });
    }
}
