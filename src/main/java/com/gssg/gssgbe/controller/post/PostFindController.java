package com.gssg.gssgbe.controller.post;

import com.gssg.gssgbe.post.dto.reponse.FindAllPostResponse;
import com.gssg.gssgbe.post.dto.reponse.PostResponse;
import com.gssg.gssgbe.post.service.PostFindService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostFindController {

  private final PostFindService postFindService;

  @Operation(summary = "전체 조회")
  @GetMapping("/api/v1/posts")
  public FindAllPostResponse findAll(
      @RequestParam(defaultValue = "0") @PositiveOrZero Integer page,
      @RequestParam(defaultValue = "10") @Positive Integer size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Slice<PostResponse> postResponses = postFindService.findAll(pageRequest);

    return new FindAllPostResponse(postResponses);
  }
}
