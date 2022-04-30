package com.gssg.gssgbe.domain.reply.repository;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.gssg.gssgbe.domain.reply.entity.ReplyLike;
import com.gssg.gssgbe.util.TestMemberInit;
import com.gssg.gssgbe.util.TestPostInit;
import com.gssg.gssgbe.util.TestReplyInit;
import com.gssg.gssgbe.util.TestSubjectInit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.gssg.gssgbe.domain.reply.repository.ReplyRepositoryImpl.SortType.LIKE_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.data.domain.Sort.Direction.DESC;

@DisplayName("[repo] 댓글 좋아요")
@Transactional
@Import({TestMemberInit.class, TestSubjectInit.class, TestPostInit.class, TestReplyInit.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReplyRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ReplyLikeRepository replyLikeRepository;

    @TestFactory
    Stream<DynamicNode> findAll() {
        // 댓글 좋아요 개수 순서를 섞어서 생성
        final Post post = postRepository.findById(1L).get();
        final List<Member> members = memberRepository.findAll();
        createReplyLike(replyRepository.getById(1L), members.subList(0, 2));
        createReplyLike(replyRepository.getById(2L), members);
        createReplyLike(replyRepository.getById(3L), members.subList(0, 3));
        createReplyLike(replyRepository.getById(4L), members.subList(0, 1));

        return Stream.of(
                dynamicContainer("조회", Stream.of(
                                dynamicTest("작성일시 순서", () -> {
                                    // given
                                    final NoOffsetPageRequest pageRequest = NoOffsetPageRequest.of(null, 5);

                                    // when
                                    final List<Reply> replies = replyRepository.findAllByPostId(post.getId(), pageRequest);

                                    // then
                                    final List<LocalDateTime> createdAts = replies.stream()
                                            .map(BaseDateTime::getCreatedAt)
                                            .collect(Collectors.toList());
                                    assertThat(createdAts).isSortedAccordingTo(Comparator.reverseOrder());
                                }),
                                dynamicTest("좋아요 순서", () -> {
                                    // given
                                    final NoOffsetPageRequest pageRequest = NoOffsetPageRequest.of(null, 5,
                                            Sort.by(DESC, LIKE_COUNT.name()));

                                    // when
                                    final List<Reply> replies = replyRepository.findAllByPostId(post.getId(), pageRequest);

                                    // then
                                    final List<Integer> likeCounts = replies.stream()
                                            .map(reply -> reply.getReplyLikes().size())
                                            .collect(Collectors.toList());
                                    assertThat(likeCounts).isSortedAccordingTo(Comparator.reverseOrder());
                                })
                        )
                )
        );
    }

    private void createReplyLike(final Reply reply, final List<Member> members) {
        members.forEach(member -> replyLikeRepository.save(new ReplyLike(reply, member)));
    }
}
