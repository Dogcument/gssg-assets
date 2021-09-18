package com.gssg.gssgbe.web.reply.response;

import org.springframework.data.domain.Slice;

import lombok.Getter;

@Getter
public class FindAllReplyResponse {

	private final Slice<ReplyResponse> replies;

	public FindAllReplyResponse(final Slice<ReplyResponse> replies) {
		this.replies = replies;
	}
}
