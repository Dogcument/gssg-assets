package com.gssg.gssgbe.domain.reply.repository;

import com.gssg.gssgbe.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {

}
