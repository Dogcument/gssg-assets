package com.gssg.gssgbe.domain.post.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.gssg.gssgbe.domain.post.entity.Post;

public interface PostRepositoryCustom {

	Slice<Post> findAllSlice(Pageable pageable);
}
