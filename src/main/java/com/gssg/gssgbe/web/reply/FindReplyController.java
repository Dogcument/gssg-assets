package com.gssg.gssgbe.web.reply;

import static com.gssg.gssgbe.domain.reply.repository.ReplyRepositoryImpl.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Operation(summary = "글의 댓글 조회")
	@GetMapping("/api/v1/posts/{postId}/replies")
	public FindAllReplyResponse findAll(
		@PathVariable final Long postId,
		@RequestParam(defaultValue = "0") @PositiveOrZero final Integer page,
		@RequestParam(defaultValue = "10") @Positive final Integer size,
		@RequestParam(defaultValue = "CREATE_AT") final SortType sortType) {
		final PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortType.name()));
		final Slice<ReplyDto> replyDtos = findReplyService.findAllByPostId(postId, pageRequest);

		return new FindAllReplyResponse(replyDtos.map(ReplyResponse::new));
	}
}
