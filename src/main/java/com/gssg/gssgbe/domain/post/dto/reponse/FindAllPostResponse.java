package com.gssg.gssgbe.domain.post.dto.reponse;

import com.gssg.gssgbe.web.post.response.PostResponse;
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
