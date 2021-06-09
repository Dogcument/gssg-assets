package com.gssg.gssgbe.controller.post;

import com.gssg.gssgbe.controller.post.response.FindAllPostResponse;
import com.gssg.gssgbe.controller.post.response.PostResponse;
import com.gssg.gssgbe.post.service.PostFindService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostFindController {

  private final PostFindService postFindService;

  @Operation(summary = "전체 조회")
  @GetMapping("/api/v1/posts")
  public FindAllPostResponse findAll() {
    List<PostResponse> postResponses = postFindService.findAll().stream()
        .map(PostResponse::new)
        .collect(Collectors.toList());

    return new FindAllPostResponse(postResponses);
  }
}
