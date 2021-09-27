package com.gssg.gssgbe.domain.reply.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.gssg.gssgbe.domain.reply.entity.Reply;

public interface ReplyRepositoryCustom {

	Slice<Reply> findAllSlice(Pageable pageable);

	Slice<Reply> findAllByPostId(final long postId, Pageable pageable);
}
