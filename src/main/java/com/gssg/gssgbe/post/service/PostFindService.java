package com.gssg.gssgbe.post.service;

import com.gssg.gssgbe.post.entity.Post;
import com.gssg.gssgbe.post.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostFindService {

  private final PostRepository postRepository;

  public List<Post> findAll() {
    return postRepository.findAll();
  }
}
