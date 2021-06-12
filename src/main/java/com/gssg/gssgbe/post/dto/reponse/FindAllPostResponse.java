package com.gssg.gssgbe.post.dto.reponse;

import org.springframework.data.domain.Slice;

public class FindAllPostResponse {

  private final Slice<PostResponse> postResponses;

  public FindAllPostResponse(Slice<PostResponse> postResponses) {
    this.postResponses = postResponses;
  }

  public Slice<PostResponse> getpostResponses() {
    return postResponses;
  }
}
