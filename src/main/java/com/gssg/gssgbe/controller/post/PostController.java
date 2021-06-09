package com.gssg.gssgbe.controller.post;

import com.gssg.gssgbe.controller.post.request.PostCreateRequest;
import com.gssg.gssgbe.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;

  @Operation(summary = "글 작성")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/api/v1/posts")
  public Long create(@RequestBody @Valid PostCreateRequest request) {
    return postService.create(request);
  }
}
