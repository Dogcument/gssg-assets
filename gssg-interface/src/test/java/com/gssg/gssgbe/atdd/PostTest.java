package com.gssg.gssgbe.atdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import com.gssg.gssgbe.common.token.Role;
import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostLikeRepository;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import com.gssg.gssgbe.util.TestUtil;
import com.gssg.gssgbe.web.post.request.CreatePostRequest;
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

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[atdd]")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private JwtAuthTokenProvider jwtAuthTokenProvider;

    private JwtAuthToken jwtAuthToken;

    @TestFactory
    Stream<DynamicNode> postTest() {
        final Subject subject = new Subject("강남역", "2호선");
        subjectRepository.save(subject);

        final AtomicLong createdPostId = new AtomicLong();

        return TestData.VALID_MEMBER().map(member -> dynamicContainer("글",
                Stream.of(
                        dynamicTest("회원 가입 & 로그인", () -> {
                            memberRepository.save(new Member(member.getEmail(), member.getPassword()));
                            jwtAuthToken = jwtAuthTokenProvider.createAuthToken(member.getEmail(), Role.MEMBER.name());
                        }),

                        dynamicContainer("글 작성", Stream.of(
                                dynamicTest("[성공] 글 작성", () -> {
                                    // given
                                    final CreatePostRequest request = new CreatePostRequest(subject.getName(), "TEST 글 작성 TEST");

                                    // when
                                    final MvcResult mvcResult = mockMvc.perform(post("/api/v1/posts")
                                                    .header(HttpHeaders.AUTHORIZATION, "bearer " + jwtAuthToken.getToken())
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(new ObjectMapper().writeValueAsString(request)))
                                            .andDo(print())
                                            .andExpect(status().isCreated())
                                            .andReturn();

                                    // then
                                    createdPostId.set(TestUtil.mvcResultToObject(mvcResult, Long.class));
                                    final Post createdPost = postRepository.findById(createdPostId.get()).get();

                                    assertThat(createdPost.getMember()).isNotNull();
                                })
                        )),

                        dynamicContainer("글 좋아요", Stream.of(
                                dynamicTest("[성공] 글 좋아요", () -> {
                                    // given

                                    // when
                                    final MvcResult mvcResult = mockMvc.perform(
                                                    post("/api/v1/posts/" + createdPostId.get() + "/like")
                                                            .header(HttpHeaders.AUTHORIZATION, "bearer " + jwtAuthToken.getToken()))
                                            .andDo(print())
                                            .andExpect(status().isCreated())
                                            .andReturn();

                                    // then
                                    final Boolean result = TestUtil.mvcResultToObject(mvcResult, Boolean.class);
                                    assertThat(result).isTrue();
                                })
                        )),

                        dynamicTest("afterAll", () -> {
                            postLikeRepository.deleteAll();
                            postRepository.deleteAll();
                            memberRepository.deleteAll();
                        })
                )));
    }
}
