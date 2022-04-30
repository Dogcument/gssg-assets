package com.gssg.gssgbe.domain.reply.entity;

import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reply_like")
@Entity
public class ReplyLike extends BaseDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_user_id")
    private Member member;

    public ReplyLike(final Reply reply, final Member member) {
        setReply(reply);
        this.member = member;
    }

    public void setReply(final Reply reply) {
        this.reply = reply;

        if (!reply.getReplyLikes().contains(this)) {
            reply.addReplyLike(this);
        }
    }

    public boolean isMine(final Member member) {
        return this.member.equals(member);
    }
}
