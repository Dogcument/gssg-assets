package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindPostService {

  private final PostRepository postRepository;

  public Slice<PostDto> findAll(Pageable pageable) {
    return postRepository.findAllSlice(pageable)
        .map(PostDto::new);
  }
}
