package com.gssg.gssgbe.post.service;

import com.gssg.gssgbe.post.dto.reponse.PostResponse;
import com.gssg.gssgbe.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostFindService {

  private final PostRepository postRepository;

  public Slice<PostResponse> findAll(Pageable pageable) {
    return postRepository.findAllSlice(pageable)
        .map(PostResponse::new);
  }
}
