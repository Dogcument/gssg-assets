package com.gssg.gssgbe.domain.reply.repository;

import static com.gssg.gssgbe.domain.reply.entity.QReply.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.gssg.gssgbe.common.util.QuerydslUtil;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<Reply> findAllSlice(final Pageable pageable) {
		final JPAQuery<Reply> jpaQuery = queryFactory
			.selectFrom(reply)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		final List<Reply> contents = jpaQuery.fetch();

		return QuerydslUtil.createSlice(contents, pageable);
	}
}
