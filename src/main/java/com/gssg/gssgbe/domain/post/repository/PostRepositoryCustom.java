package com.gssg.gssgbe.domain.post.repository;

import com.gssg.gssgbe.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {

  Slice<Post> findAllSlice(Pageable pageable);
}
