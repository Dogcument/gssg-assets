package com.gssg.gssgbe.web.post;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.service.FindPostService;
import com.gssg.gssgbe.web.post.response.FindAllPostResponse;
import com.gssg.gssgbe.web.post.response.PostResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "글")
@Validated
@RequiredArgsConstructor
@RestController
public class FindPostController {

	private final FindPostService findPostService;

	@Operation(summary = "전체 조회")
	@GetMapping("/api/v1/posts")
	public FindAllPostResponse findAll(
		@RequestParam(defaultValue = "0") @PositiveOrZero final Integer page,
		@RequestParam(defaultValue = "10") @Positive final Integer size) {
		final PageRequest pageRequest = PageRequest.of(page, size);
		final Slice<PostDto> postDtos = findPostService.findAll(pageRequest);

		return new FindAllPostResponse(postDtos.map(PostResponse::new));
	}
}
