package com.gssg.gssgbe.controller.post.response;

import java.util.Collections;
import java.util.List;

public class FindAllPostResponse {

  private final List<PostResponse> postResponses;

  public FindAllPostResponse(List<PostResponse> postResponses) {
    this.postResponses = postResponses;
  }

  public List<PostResponse> getpostResponses() {
    return Collections.unmodifiableList(postResponses);
  }
}
