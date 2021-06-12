package com.gssg.gssgbe.user.dto.response;

import java.util.Collections;
import java.util.List;

public class FindAllUserResponse {

  private final List<UserResponse> userResponses;

  public FindAllUserResponse(List<UserResponse> userResponses) {
    this.userResponses = userResponses;
  }

  public List<UserResponse> getUserResponses() {
    return Collections.unmodifiableList(userResponses);
  }
}
