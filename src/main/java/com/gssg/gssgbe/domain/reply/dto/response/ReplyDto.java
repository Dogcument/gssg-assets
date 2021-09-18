package com.gssg.gssgbe.domain.reply.dto.response;

import java.time.LocalDateTime;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.reply.entity.Reply;

import lombok.Getter;

@Getter
public class ReplyDto {

	private final long id;
	private final String content;
	private final MemberDto memberDto;
	private final long likeCount;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public ReplyDto(final Reply reply) {
		this.id = reply.getId();
		this.content = reply.getContent();
		this.memberDto = new MemberDto(reply.getMember());
		this.likeCount = reply.getReplyLikes().size();
		this.createdAt = reply.getCreatedAt();
		this.updatedAt = reply.getUpdatedAt();
	}
}
