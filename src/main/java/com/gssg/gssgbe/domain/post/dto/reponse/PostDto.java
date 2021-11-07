package com.gssg.gssgbe.domain.post.dto.reponse;

import java.time.LocalDateTime;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.subject.dto.SubjectDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDto {

	private final long id;
	private final SubjectDto subjectDto;
	private final String content;
	private final MemberDto memberDto;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final Boolean like;
	private final long likeCount;
	private final long replyCount;

	public static PostDto of(final Post post, final boolean like) {
		return PostDto.builder()
			.id(post.getId())
			.subjectDto(SubjectDto.of(post.getSubject()))
			.content(post.getContent())
			.memberDto(MemberDto.of(post.getMember()))
			.createdAt(post.getCreatedAt())
			.updatedAt(post.getUpdatedAt())
			.like(like)
			.likeCount(post.getPostLikes().size())
			.replyCount(post.getReplies().size())
			.build();
	}

	public static PostDto of(final Post post) {
		return of(post, false);
	}
}
