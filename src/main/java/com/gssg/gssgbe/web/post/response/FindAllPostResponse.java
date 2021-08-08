package com.gssg.gssgbe.web.post.response;

import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindAllPostResponse {

    private final Slice<PostResponse> posts;

    public FindAllPostResponse(Slice<PostResponse> posts) {
        this.posts = posts;
    }
}
