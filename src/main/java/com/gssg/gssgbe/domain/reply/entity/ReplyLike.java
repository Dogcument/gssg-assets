package com.gssg.gssgbe.domain.reply.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.domain.member.entity.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reply_like")
@Entity
public class ReplyLike extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_like_id")
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

		if (!reply.getReplyLike().contains(this)) {
			reply.addReplyLike(this);
		}
	}
}
