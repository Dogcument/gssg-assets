package com.gssg.gssgbe.post.service;

import com.gssg.gssgbe.controller.post.request.PostCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

  private final PostCreateService postCreateService;

  public long create(PostCreateRequest request) {
    return postCreateService.create(request);
  }
}
