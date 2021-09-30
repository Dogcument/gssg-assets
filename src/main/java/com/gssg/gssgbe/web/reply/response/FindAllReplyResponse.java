package com.gssg.gssgbe.web.reply.response;

import java.util.List;

import lombok.Getter;

@Getter
public class FindAllReplyResponse {

	private final List<ReplyResponse> replies;

	public FindAllReplyResponse(final List<ReplyResponse> replies) {
		this.replies = replies;
	}
}
