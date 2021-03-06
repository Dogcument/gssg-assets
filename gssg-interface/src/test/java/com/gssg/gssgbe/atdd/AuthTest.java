package com.gssg.gssgbe.atdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        return TestData.VALID_MEMBER().map(member -> dynamicContainer(
            "??????",
            Stream.of(
                dynamicTest("?????? ??????", () ->
                    memberRepository.save(new Member(member.getEmail(), member.getPassword()))
                ),

                dynamicContainer("?????????", Stream.of(
                    dynamicTest("[??????] ?????????", () -> {
                        // given
                        final LoginRequest request = new LoginRequest(
                            member.getEmail(),
                            member.getPassword()
                        );

                        // when
                        final MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();

                        // then
                        final LoginResponse response = TestUtil.mvcResultToObject(
                            mvcResult,
                            LoginResponse.class
                        );
                        final JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(
                            response.getJwt()

                        );
                        assertThat(jwtAuthToken.getSubject()).isNotBlank();
                        refreshToken = refreshTokenProvider.convertAuthToken(
                            response.getRefreshToken());
                        assertThat(refreshToken.getSubject()).isNotBlank();
                    }),

                    dynamicTest("[??????] request body ??????", () -> {
                        // given

                        // when
                        mockMvc.perform(post("/api/v1/auth/login"))
                            .andDo(print())
                            .andExpect(status().isBadRequest());
                    })
                )),

                dynamicContainer("Refresh Token", Stream.of(
                    dynamicTest("[??????] JWT ?????????", () -> {
                        // given
                        final RefreshRequest request = new RefreshRequest(refreshToken.getToken());

                        // when
                        final MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/refresh")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();

                        // then
                        final RefreshResponse response = TestUtil.mvcResultToObject(
                            mvcResult,
                            RefreshResponse.class
                        );
                        final JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(
                            response.getJwt());
                        assertThat(jwtAuthToken.getSubject()).isNotBlank();
                    }),

                    dynamicTest("[??????] token ?????? ??????", () -> {
                        // given
                        final RefreshRequest request = new RefreshRequest(
                            refreshToken.getToken() + 1);

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
            )
        ));
    }
}
