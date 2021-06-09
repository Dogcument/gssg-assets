package com.gssg.gssgbe.controller.post.response;

import com.gssg.gssgbe.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostResponse {

  @Schema(description = "글 PK")
  private final Long id;

  @Schema(description = "내용")
  private final String content;

  // TODO 권한 작업 후 개발
//  @Schema(description = "작성자")
//  private final UserResponse userResponse;

  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public PostResponse(Post post) {
    id = post.getId();
    content = post.getContent();
//    userResponse = new UserResponse(post.getWriter());
    createdAt = post.getCreatedAt();
    updatedAt = post.getUpdatedAt();
  }
}
