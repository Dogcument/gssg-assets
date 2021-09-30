package com.gssg.gssgbe.domain.reply.repository;

import java.util.List;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.reply.entity.Reply;

public interface ReplyRepositoryCustom {

	List<Reply> findAllByPostId(final long postId, final NoOffsetPageRequest pageRequest);
}
