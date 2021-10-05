package com.gssg.gssgbe.web.post.response;

import java.util.List;

import lombok.Getter;

@Getter
public class FindAllPostResponse {

	private final List<PostResponse> posts;

	public FindAllPostResponse(final List<PostResponse> posts) {
		this.posts = posts;
	}
}
