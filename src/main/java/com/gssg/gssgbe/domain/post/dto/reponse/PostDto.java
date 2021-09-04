package com.gssg.gssgbe.domain.post.dto.reponse;

import java.time.LocalDateTime;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.subject.dto.SubjectDto;

import lombok.Getter;

@Getter
public class PostDto {

	private final long id;
	private final SubjectDto subjectDto;
	private final String content;
	private final MemberDto memberDto;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final long likeCount;

	public PostDto(final Post post) {
		this.id = post.getId();
		this.subjectDto = new SubjectDto(post.getSubject());
		this.content = post.getContent();
		this.memberDto = new MemberDto(post.getMember());
		this.createdAt = post.getCreatedAt();
		this.updatedAt = post.getUpdatedAt();
		this.likeCount = post.getPostLike().size();
	}
}
