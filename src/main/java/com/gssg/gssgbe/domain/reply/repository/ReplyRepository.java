package com.gssg.gssgbe.domain.reply.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {

	Slice<Reply> findAllSlice(Pageable pageable);

	Slice<Reply> findAllByMember(Member member, Pageable pageable);
}
