package com.gssg.gssgbe.web.reply;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.service.CreateReplyLikeService;
import com.gssg.gssgbe.domain.reply.service.CreateReplyService;
import com.gssg.gssgbe.domain.reply.service.DeleteReplyLikeService;
import com.gssg.gssgbe.domain.reply.service.FindReplyLikeService;
import com.gssg.gssgbe.web.reply.request.CreateReplyRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "댓글")
@RequiredArgsConstructor
@RestController
public class ReplyController {

	private final CreateReplyService createReplyService;
	private final FindReplyLikeService findReplyLikeService;
	private final CreateReplyLikeService createReplyLikeService;
	private final DeleteReplyLikeService deleteReplyLikeService;

	@Operation(summary = "댓글 작성", security = @SecurityRequirement(name = "bearerAuth"))
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/replies")
	public Long create(
		@Parameter(hidden = true) @LoginMember final Member loginMember,
		@RequestBody @Valid final CreateReplyRequest request) {
		return createReplyService.create(request.toDto(loginMember));
	}

	@Operation(summary = "댓글 좋아요 토글", security = @SecurityRequirement(name = "bearerAuth"))
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/replies/{postId}/like")
	public Boolean like(
		@Parameter(hidden = true) @LoginMember final Member loginMember,
		@PathVariable final Long postId) {
		if (findReplyLikeService.exist(postId, loginMember)) {
			deleteReplyLikeService.delete(postId, loginMember);
			return false;
		}

		createReplyLikeService.create(postId, loginMember);
		return true;
	}
}
