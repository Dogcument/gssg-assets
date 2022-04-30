package com.gssg.gssgbe.domain.post.entity;

import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
@Entity
public class Post extends BaseDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String content;

    private Boolean deleted;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "post")
    private List<Reply> replies;

    private Post(final Member member, final Subject subject, final String content) {
        this.member = member;
        this.subject = subject;
        this.content = content;
        this.deleted = false;
        this.postLikes = new ArrayList<>();
        this.replies = new ArrayList<>();
    }

    public static Post of(final Member member, final Subject subject, final String content) {
        return new Post(member, subject, content);
    }

    public void addPostLike(final PostLike postLike) {
        this.postLikes.add(postLike);

        if (postLike.getPost() != this) {
            postLike.setPost(this);
        }
    }

    public void addReply(final Reply reply) {
        this.replies.add(reply);

        if (reply.getPost() != this) {
            reply.setPost(this);
        }
    }

    public boolean isLike(final Member member) {
        return postLikes.stream()
                .anyMatch(like -> like.isMine(member));
    }
}
