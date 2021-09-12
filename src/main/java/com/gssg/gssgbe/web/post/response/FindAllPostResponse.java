package com.gssg.gssgbe.web.post.response;

import org.springframework.data.domain.Slice;

import lombok.Getter;

@Getter
public class FindAllPostResponse {

	private final Slice<PostResponse> posts;

	public FindAllPostResponse(final Slice<PostResponse> posts) {
		this.posts = posts;
	}
}
