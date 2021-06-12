package com.gssg.gssgbe.post.repository;

import com.gssg.gssgbe.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {

  Slice<Post> findAllSlice(Pageable pageable);
}
