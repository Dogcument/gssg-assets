package com.gssg.gssgbe.web.post.response;

import java.util.List;
import java.util.stream.Collectors;

import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindAllPostResponse {

	private final List<PostResponse> posts;

	public static FindAllPostResponse of(final List<PostDto> posts) {
		return FindAllPostResponse.builder()
			.posts(posts.stream()
				.map(PostResponse::of)
				.collect(Collectors.toList()))
			.build();
	}
}
