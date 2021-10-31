package com.gssg.gssgbe.domain.reply.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.entity.Post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reply")
@Entity
public class Reply extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_user_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	private String content;

	private Boolean deleted;

	@OneToMany(mappedBy = "reply")
	private List<ReplyLike> replyLikes;

	public Reply(final Member member, final Post post, final String content) {
		this.member = member;
		this.post = post;
		this.content = content;
		this.deleted = false;
		this.replyLikes = new ArrayList<>();
	}

	public void addReplyLike(final ReplyLike replyLike) {
		this.replyLikes.add(replyLike);

		if (replyLike.getReply() != this) {
			replyLike.setReply(this);
		}
	}

	public void setPost(final Post post) {
		this.post = post;

		if (!post.getReplies().contains(this)) {
			post.addReply(this);
		}
	}

	public boolean isLike(final Member member) {
		return replyLikes.stream()
			.anyMatch(like -> like.isMine(member));
	}
}
