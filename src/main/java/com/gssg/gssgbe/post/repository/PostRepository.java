package com.gssg.gssgbe.post.repository;

import com.gssg.gssgbe.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

  Slice<Post> findAllSlice(Pageable pageable);
}
