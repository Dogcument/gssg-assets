package com.gssg.gssgbe.atdd;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import com.gssg.gssgbe.common.token.RefreshToken;
import com.gssg.gssgbe.common.token.RefreshTokenProvider;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.util.TestUtil;
import com.gssg.gssgbe.web.auth.request.LoginRequest;
import com.gssg.gssgbe.web.auth.request.RefreshRequest;
import com.gssg.gssgbe.web.auth.response.LoginResponse;
import com.gssg.gssgbe.web.auth.response.RefreshResponse;

@DisplayName("[atdd]")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private JwtAuthTokenProvider jwtAuthTokenProvider;

	@Autowired
	private RefreshTokenProvider refreshTokenProvider;

	private RefreshToken refreshToken;

	@TestFactory
	Stream<DynamicNode> authTest() {
		return TestData.VALID_MEMBER().map(member -> dynamicContainer("인증",
			Stream.of(
				dynamicTest("회원 가입", () ->
					memberRepository.save(new Member(member.getEmail(), member.getPassword()))
				),

				dynamicContainer("로그인", Stream.of(
					dynamicTest("[성공] 로그인", () -> {
						// given
						LoginRequest request = new LoginRequest(member.getEmail(), member.getPassword());

						// when
						MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/login")
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(request)))
							.andDo(print())
							.andExpect(status().isOk())
							.andReturn();

						// then
						LoginResponse response = TestUtil.mvcResultToObject(mvcResult, LoginResponse.class);
						JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(response.getJwt());
						assertThat(jwtAuthToken.getSubject()).isNotBlank();
						refreshToken = refreshTokenProvider.convertAuthToken(response.getRefreshToken());
						assertThat(refreshToken.getSubject()).isNotBlank();
					}),

					dynamicTest("[실패] request body 없음", () -> {
						// given

						// when
						mockMvc.perform(post("/api/v1/auth/login"))
							.andDo(print())
							.andExpect(status().isBadRequest());
					})
				)),

				dynamicContainer("Refresh Token", Stream.of(
					dynamicTest("[성공] JWT 재발급", () -> {
						// given
						RefreshRequest request = new RefreshRequest(refreshToken.getToken());

						// when
						MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/refresh")
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(request)))
							.andDo(print())
							.andExpect(status().isOk())
							.andReturn();

						// then
						RefreshResponse response = TestUtil.mvcResultToObject(mvcResult, RefreshResponse.class);
						JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(response.getJwt());
						assertThat(jwtAuthToken.getSubject()).isNotBlank();
					}),

					dynamicTest("[실패] token 해석 불가", () -> {
						// given
						RefreshRequest request = new RefreshRequest(refreshToken.getToken() + 1);

						// when
						mockMvc.perform(post("/api/v1/auth/refresh")
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(request)))
							.andDo(print())
							.andExpect(status().isUnauthorized())
							.andReturn();

						// then
					})
				)),

				dynamicTest("afterAll", () -> memberRepository.deleteAll())
			)));
	}
}
