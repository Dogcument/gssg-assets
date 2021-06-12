package com.gssg.gssgbe.post.service;

import com.gssg.gssgbe.post.dto.request.PostCreateRequest;
import com.gssg.gssgbe.post.entity.Post;
import com.gssg.gssgbe.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostCreateService {

  private final PostRepository postRepository;

  public long create(PostCreateRequest request) {
    Post post = new Post(null, request.getContent());

    return postRepository.save(post).getId();
  }
}
