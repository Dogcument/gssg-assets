package com.gssg.gssgbe.controller.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.dto.request.LoginMemberRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("[web] 로그인")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MemberRepository memberRepository;

  public static Stream<Member> VALID_MEMBER() {
    return TestData.VALID_MEMBER();
  }

  @DisplayName("[성공]")
  @ParameterizedTest
  @MethodSource("VALID_MEMBER")
  public void success(Member member) throws Exception {
    // given
    memberRepository.save(member);

    LoginMemberRequest request = new LoginMemberRequest(member.getEmail(), member.getPassword());

    // when
    mockMvc.perform(post("/api/v1/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk());

    // then

  }

  @DisplayName("[실패]")
  @ParameterizedTest
  @MethodSource("VALID_MEMBER")
  public void success_wrongPassword(Member member) throws Exception {
    // given
    memberRepository.save(member);

    LoginMemberRequest request = new LoginMemberRequest(member.getEmail(), member.getPassword() + "1");

    // when
    mockMvc.perform(post("/api/v1/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isUnauthorized());

    // then

  }
}
