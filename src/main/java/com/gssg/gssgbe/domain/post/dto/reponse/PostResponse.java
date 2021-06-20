package com.gssg.gssgbe.domain.post.dto.reponse;

import com.gssg.gssgbe.domain.post.entity.Post;
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
//  private final MemberResponse memberResponse;

  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public PostResponse(Post post) {
    id = post.getId();
    content = post.getContent();
//    memberResponse = new MemberResponse(post.getWriter());
    createdAt = post.getCreatedAt();
    updatedAt = post.getUpdatedAt();
  }
}
