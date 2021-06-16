package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.domain.post.dto.request.CreatePostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

  private final CreatePostService createPostService;

  public long create(CreatePostRequestDto request) {
    return createPostService.create(request);
  }
}
