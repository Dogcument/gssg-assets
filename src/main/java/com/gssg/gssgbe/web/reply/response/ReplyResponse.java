package com.gssg.gssgbe.web.reply.response;

import java.time.LocalDateTime;

import com.gssg.gssgbe.domain.reply.dto.response.ReplyDto;
import com.gssg.gssgbe.web.member.response.MemberResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReplyResponse {

	@Schema(description = "댓글 id")
	private final Long id;

	@Schema(description = "작성자")
	private final MemberResponse member;

	@Schema(description = "내용")
	private final String content;

	@Schema(description = "좋아요 수")
	private final Long likeCount;

	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public ReplyResponse(final ReplyDto dto) {
		this.id = dto.getId();
		this.member = new MemberResponse(dto.getMemberDto());
		this.content = dto.getContent();
		this.likeCount = dto.getLikeCount();
		this.createdAt = dto.getCreatedAt();
		this.updatedAt = dto.getUpdatedAt();
	}
}
