package com.gssg.gssgbe.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gssg.gssgbe.domain.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

}
