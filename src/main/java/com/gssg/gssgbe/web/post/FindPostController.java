package com.gssg.gssgbe.web.post;

import com.gssg.gssgbe.domain.post.dto.reponse.FindAllPostResponse;
import com.gssg.gssgbe.domain.post.dto.reponse.PostResponse;
import com.gssg.gssgbe.domain.post.service.FindPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "글")
@RequiredArgsConstructor
@RestController
public class FindPostController {

  private final FindPostService findPostService;

  @Operation(summary = "전체 조회")
  @GetMapping("/api/v1/posts")
  public FindAllPostResponse findAll(
      @RequestParam(defaultValue = "0") @PositiveOrZero Integer page,
      @RequestParam(defaultValue = "10") @Positive Integer size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Slice<PostResponse> postResponses = findPostService.findAll(pageRequest);

    return new FindAllPostResponse(postResponses);
  }
}
