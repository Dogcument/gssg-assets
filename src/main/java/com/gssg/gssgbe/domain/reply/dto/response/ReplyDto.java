package com.gssg.gssgbe.domain.reply.dto.response;

import java.time.LocalDateTime;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.reply.entity.Reply;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyDto {

	private final long id;
	private final String content;
	private final MemberDto memberDto;
	private final Boolean like;
	private final long likeCount;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public static ReplyDto of(final Reply reply, final boolean like) {
		return ReplyDto.builder()
			.id(reply.getId())
			.content(reply.getContent())
			.memberDto(new MemberDto(reply.getMember()))
			.like(like)
			.likeCount(reply.getReplyLikes().size())
			.createdAt(reply.getCreatedAt())
			.updatedAt(reply.getUpdatedAt())
			.build();
	}

	public static ReplyDto of(final Reply reply) {
		return ReplyDto.of(reply, false);
	}
}
