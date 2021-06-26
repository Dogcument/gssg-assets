package com.gssg.gssgbe.web.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshRequest {

  @Schema(description = "Refresh Token")
  @NotEmpty
  private String refreshToken;
}

