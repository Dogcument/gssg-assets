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
import com.gssg.gssgbe.common.token.Role;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.util.TestUtil;
import com.gssg.gssgbe.web.post.request.CreatePostRequest;
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
class PostTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JwtAuthTokenProvider jwtAuthTokenProvider;

    private JwtAuthToken jwtAuthToken;

    @TestFactory
    Stream<DynamicNode> postTest() {
        return TestData.VALID_MEMBER().map(member -> dynamicContainer("글",
            Stream.of(
                dynamicTest("회원 가입 & 로그인", () -> {
                    memberRepository.save(new Member(member.getEmail(), member.getPassword()));
                    jwtAuthToken = jwtAuthTokenProvider.createAuthToken(member.getEmail(), Role.MEMBER.name());
                }),

                dynamicContainer("글 작성", Stream.of(
                    dynamicTest("[성공] 글 작성", () -> {
                        // given
                        CreatePostRequest request = new CreatePostRequest("TEST 글 작성 TEST");

                        // when
                        MvcResult mvcResult = mockMvc.perform(post("/api/v1/posts")
                            .header(HttpHeaders.AUTHORIZATION, "bearer " + jwtAuthToken.getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(request)))
                            .andDo(print())
                            .andExpect(status().isCreated())
                            .andReturn();

                        // then
                        Long createdPostId = TestUtil.mvcResultToObject(mvcResult, Long.class);
                        Post createdPost = postRepository.findById(createdPostId).get();

                        assertThat(createdPost.getWriter()).isNotNull();
                    })
                )),

                dynamicTest("afterAll", () -> {
                    postRepository.deleteAll();
                    memberRepository.deleteAll();
                })
            )));
    }
}
