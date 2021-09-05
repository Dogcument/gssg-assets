package com.gssg.gssgbe.domain.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.entity.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

	Optional<PostLike> findByPostAndMember(final Post post, final Member member);
}
