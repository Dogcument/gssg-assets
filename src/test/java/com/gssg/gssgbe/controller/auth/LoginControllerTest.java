package com.gssg.gssgbe.controller.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.controller.auth.response.LoginResponse;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.dto.request.LoginMemberRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.util.TestUtil;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@DisplayName("[web] 로그인")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MemberRepository memberRepository;

  @DisplayName("로그인")
  @TestFactory
  Stream<DynamicNode> login() {
    return TestData.VALID_MEMBER().map(member -> dynamicContainer("로그인", Stream.of(

        dynamicTest("테스트 회원 가입", () ->
            memberRepository.save(new Member(
                member.getEmail(),
                member.getPassword(),
                member.getNickName()))),

        dynamicTest("[성공]", () -> {
          // given
          LoginMemberRequest request = new LoginMemberRequest(member.getEmail(), member.getPassword());

          // when
          MvcResult mvcResult = mockMvc.perform(post("/api/v1/login")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new ObjectMapper().writeValueAsString(request)))
              .andDo(print())
              .andExpect(status().isOk())
              .andReturn();

          // then
          LoginResponse response = TestUtil.mvcResultToObject(mvcResult, LoginResponse.class);
          JwtAuthToken jwtAuthToken = TestUtil.jwtAuthTokenProvider.convertAuthToken(response.getToken());
          assertThat(jwtAuthToken.getSubject()).isNotBlank();
        }),

        dynamicTest("[실패] request body 없음", () -> {
          // given

          // when
          mockMvc.perform(post("/api/v1/login"))
              .andDo(print())
              .andExpect(status().isBadRequest());
        })
    )));
  }
}
