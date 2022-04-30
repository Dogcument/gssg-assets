package com.gssg.gssgbe.domain.post.entity;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.repository.PostLikeRepository;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("좋아요")
@SpringBootTest
class PostMemberLikeTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Test
    void create() {
        // given
        final Member member = new Member("choi@gmail.com", "1234");
        memberRepository.save(member);
        final Member member2 = new Member("choi2@gmail.com", "1234");
        memberRepository.save(member2);

        final Subject subject = new Subject("subject name", "description");
        subjectRepository.save(subject);

        final Post post = Post.of(member, subject, "content");
        postRepository.save(post);

        // when
        final PostLike postLike = new PostLike(post, member);
        postLikeRepository.save(postLike);
        final PostLike postLike2 = new PostLike(post, member2);
        postLikeRepository.save(postLike2);

        // then
        assertThat(post.getPostLikes().stream().map(PostLike::getPost)).anyMatch(e -> e.equals(post));
        assertThat(post.getPostLikes().stream().map(PostLike::getMember)).anyMatch(e -> e.equals(member));
        assertThat(post.getPostLikes().stream().map(PostLike::getMember)).anyMatch(e -> e.equals(member2));
    }
}
