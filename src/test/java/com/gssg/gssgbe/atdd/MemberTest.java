package com.gssg.gssgbe.atdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import com.gssg.gssgbe.common.token.Role;
import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.util.TestUtil;
import com.gssg.gssgbe.web.member.request.UpdateMemberRequest;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@DisplayName("[atdd]")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MemberTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtAuthTokenProvider jwtAuthTokenProvider;

    private Member savedMember;
    private JwtAuthToken jwtAuthToken;

    @TestFactory
    Stream<DynamicNode> memberTest() {
        return TestData.VALID_MEMBER().map(member -> dynamicContainer("회원",
            Stream.of(
                dynamicTest("회원 가입 & 로그인", () -> {
                    savedMember = memberRepository.save(new Member(member.getEmail(), member.getPassword()));
                    jwtAuthToken = jwtAuthTokenProvider.createAuthToken(member.getEmail(), Role.MEMBER.name());
                }),

                dynamicContainer("회원 정보 수정", Stream.of(
                    dynamicTest("[성공] 회원 정보 수정", () -> {
                        // given
                        String nickName = "단단이";
                        UpdateMemberRequest request = new UpdateMemberRequest(nickName, ProfileDogType.CORGI);

                        // when
                        mockMvc.perform(patch("/api/v1/members/")
                            .header(HttpHeaders.AUTHORIZATION, "bearer " + jwtAuthToken.getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(request)))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();

                        // then
                        savedMember = memberRepository.findById(savedMember.getId()).get();
                        assertThat(savedMember.getNickName()).isEqualTo(nickName);
                    })
                )),

                dynamicContainer("내 정보 조회", Stream.of(
                    dynamicTest("[성공] 내 정보 조회", () -> {
                        // given

                        // when
                        MvcResult mvcResult = mockMvc.perform(get("/api/v1/my/info")
                            .header(HttpHeaders.AUTHORIZATION, "bearer " + jwtAuthToken.getToken()))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();

                        // then
                        MemberResponse memberResponse = TestUtil.mvcResultToObject(mvcResult, MemberResponse.class);
                        assertThat(memberResponse.getEmail()).isNotNull();
                    })
                )),

                dynamicTest("afterAll", () -> memberRepository.deleteAll())
            )));
    }
}
