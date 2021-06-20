package com.gssg.gssgbe.domain.member.dto.response;

import com.gssg.gssgbe.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberResponse {

  @Schema(description = "회원 PK")
  private final Long id;

  @Schema(description = "로그인 ID")
  private final String email;

  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public MemberResponse(Member member) {
    this.id = member.getId();
    this.email = member.getEmail();
    this.createdAt = member.getCreatedAt();
    this.updatedAt = member.getUpdatedAt();
  }
}
