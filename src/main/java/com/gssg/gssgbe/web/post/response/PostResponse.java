package com.gssg.gssgbe.web.post.response;

import java.time.LocalDateTime;

import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import com.gssg.gssgbe.web.subject.response.SubjectResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

	@Schema(description = "글 PK")
	private final Long id;

	@Schema(description = "내용")
	private final SubjectResponse subject;

	@Schema(description = "내용")
	private final String content;

	@Schema(description = "작성자")
	private final MemberResponse member;

	@Schema(description = "좋아요")
	private final Boolean like;

	@Schema(description = "좋아요 수")
	private final Long likeCount;

	@Schema(description = "댓글 수")
	private final Long replyCount;

	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public static PostResponse of(final PostDto dto) {
		return PostResponse.builder()
			.id(dto.getId())
			.subject(SubjectResponse.of(dto.getSubjectDto()))
			.content(dto.getContent())
			.member(new MemberResponse(dto.getMemberDto()))
			.like(dto.getLike())
			.likeCount(dto.getLikeCount())
			.replyCount(dto.getReplyCount())
			.createdAt(dto.getCreatedAt())
			.updatedAt(dto.getUpdatedAt())
			.build();
	}
}
