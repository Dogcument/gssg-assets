package com.gssg.gssgbe.domain.post.dto.reponse;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostDto {

  private final Long id;
  private final String content;
  private final MemberDto memberDto;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public PostDto(Post post) {
    id = post.getId();
    content = post.getContent();
    memberDto = new MemberDto(post.getWriter());
    createdAt = post.getCreatedAt();
    updatedAt = post.getUpdatedAt();
  }
}
