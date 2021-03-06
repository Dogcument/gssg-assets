package com.gssg.gssgbe.domain.reply.repository;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.gssg.gssgbe.domain.reply.entity.ReplyLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {

    Optional<ReplyLike> findByReplyAndMember(final Reply reply, final Member member);
}
