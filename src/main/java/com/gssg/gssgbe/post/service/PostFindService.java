package com.gssg.gssgbe.post.service;

import com.gssg.gssgbe.post.dto.reponse.PostResponse;
import com.gssg.gssgbe.post.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostFindService {

  private final PostRepository postRepository;

  public List<PostResponse> findAll() {
    return postRepository.findAll().stream()
        .map(PostResponse::new)
        .collect(Collectors.toList());
  }
}
