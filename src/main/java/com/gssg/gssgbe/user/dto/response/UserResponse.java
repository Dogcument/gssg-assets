package com.gssg.gssgbe.user.dto.response;

import com.gssg.gssgbe.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserResponse {

  @Schema(description = "회원 PK")
  private final Long id;

  @Schema(description = "로그인 ID")
  private final String loginId;

  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public UserResponse(User user) {
    this.id = user.getId();
    this.loginId = user.getLoginId();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
  }
}
