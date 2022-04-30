package com.gssg.gssgbe.domain.reply.repository;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.reply.entity.Reply;

import java.util.List;

public interface ReplyRepositoryCustom {

    List<Reply> findAllByPostId(final long postId, final NoOffsetPageRequest pageRequest);
}
