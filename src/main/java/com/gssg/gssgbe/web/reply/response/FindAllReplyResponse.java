package com.gssg.gssgbe.web.reply.response;

import java.util.List;
import java.util.stream.Collectors;

import com.gssg.gssgbe.domain.reply.dto.response.ReplyDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindAllReplyResponse {

	private final List<ReplyResponse> replies;

	public static FindAllReplyResponse of(final List<ReplyDto> replies) {
		return FindAllReplyResponse.builder()
			.replies(replies.stream()
				.map(ReplyResponse::of)
				.collect(Collectors.toList()))
			.build();
	}
}
