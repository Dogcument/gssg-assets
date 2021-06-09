package com.gssg.gssgbe.post.dto.reponse;

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
