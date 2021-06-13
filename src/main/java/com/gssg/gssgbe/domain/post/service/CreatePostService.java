package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.domain.post.dto.request.CreatePostRequest;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostService {

  private final PostRepository postRepository;

  public long create(CreatePostRequest request) {
    Post post = new Post(null, request.getContent());

    return postRepository.save(post).getId();
  }
}
