package com.gssg.gssgbe.web.reply;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.domain.reply.dto.response.ReplyDto;
import com.gssg.gssgbe.domain.reply.service.FindReplyService;
import com.gssg.gssgbe.web.reply.response.FindAllReplyResponse;
import com.gssg.gssgbe.web.reply.response.ReplyResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "댓글")
@Validated
@RequiredArgsConstructor
@RestController
public class FindReplyController {

	private final FindReplyService findReplyService;

	@Operation(summary = "전체 조회")
	@GetMapping("/api/v1/replies")
	public FindAllReplyResponse findAll(
		@RequestParam(defaultValue = "0") @PositiveOrZero final Integer page,
		@RequestParam(defaultValue = "10") @Positive final Integer size) {
		final PageRequest pageRequest = PageRequest.of(page, size);
		final Slice<ReplyDto> replyDtos = findReplyService.findAll(pageRequest);

		return new FindAllReplyResponse(replyDtos.map(ReplyResponse::new));
	}
}
