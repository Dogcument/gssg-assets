package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.domain.post.dto.request.CreatePostRequestDto;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostService {

  private final PostRepository postRepository;

  public long create(CreatePostRequestDto request) {
    Post post = new Post(request.getWriter(), request.getContent());

    return postRepository.save(post).getId();
  }
}
