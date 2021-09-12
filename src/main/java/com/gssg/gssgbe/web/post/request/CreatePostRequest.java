package com.gssg.gssgbe.web.post.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.dto.request.CreatePostRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePostRequest {

	@Schema(description = "글감 이름")
	@Length(max = 255)
	@NotEmpty
	private String subjectName;

	@Schema(description = "글 내용")
	@Length(max = 255)
	@NotEmpty
	private String content;

	public CreatePostRequestDto toDto(final Member member) {
		return new CreatePostRequestDto(member, subjectName, content);
	}
}
