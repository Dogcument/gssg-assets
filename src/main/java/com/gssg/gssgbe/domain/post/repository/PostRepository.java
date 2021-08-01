package com.gssg.gssgbe.domain.post.repository;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    Slice<Post> findAllSlice(Pageable pageable);

    Slice<Post> findAllByMember(Member member, Pageable pageable);
}
