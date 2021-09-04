package com.gssg.gssgbe.web.post.response;

import java.time.LocalDateTime;

import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import com.gssg.gssgbe.web.subject.response.SubjectResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PostResponse {

	@Schema(description = "글 PK")
	private final Long id;

	@Schema(description = "내용")
	private final SubjectResponse subject;

	@Schema(description = "내용")
	private final String content;

	@Schema(description = "작성자")
	private final MemberResponse member;

	@Schema(description = "좋아요 수")
	private final Long likeCount;

	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public PostResponse(final PostDto dto) {
		this.id = dto.getId();
		this.subject = new SubjectResponse(dto.getSubjectDto());
		this.content = dto.getContent();
		this.member = new MemberResponse(dto.getMemberDto());
		this.likeCount = dto.getLikeCount();
		this.createdAt = dto.getCreatedAt();
		this.updatedAt = dto.getUpdatedAt();
	}
}
