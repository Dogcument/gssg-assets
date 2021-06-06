package com.gssg.gssgbe.controller.user.response;

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
