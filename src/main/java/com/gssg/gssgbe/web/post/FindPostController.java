package com.gssg.gssgbe.web.post;

import static com.gssg.gssgbe.domain.post.repository.PostRepositoryImpl.*;

import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.service.FindPostService;
import com.gssg.gssgbe.web.post.response.FindAllPostResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "글")
@Validated
@RequiredArgsConstructor
@RestController
public class FindPostController {

	private final FindPostService findPostService;

	@Operation(summary = "전체 조회", security = @SecurityRequirement(name = "bearerAuth"), description = "좋아요 순서는 paging 불가")
	@GetMapping("/api/v1/posts")
	public FindAllPostResponse findAll(
		@Parameter(hidden = true) @LoginMember final Member loginMember,
		@RequestParam @Nullable @Positive final Long currentPostId,
		@RequestParam(defaultValue = "10") @Positive final Integer size,
		@RequestParam(defaultValue = "ID") final SortType sortType) {
		final NoOffsetPageRequest pageRequest = NoOffsetPageRequest.of(currentPostId, size, Sort.by(sortType.name()));
		final List<PostDto> postDtos = findPostService.findAll(loginMember, pageRequest);

		return FindAllPostResponse.of(postDtos);
	}
}
