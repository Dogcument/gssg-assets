package com.gssg.gssgbe.domain.post.repository;

import static com.gssg.gssgbe.domain.post.entity.QPost.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.gssg.gssgbe.common.util.QuerydslUtil;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<Post> findAllSlice(final Pageable pageable) {
		final JPAQuery<Post> jpaQuery = queryFactory
			.selectFrom(post)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		final List<Post> content = jpaQuery.fetch();

		return QuerydslUtil.createSlice(content, pageable);
	}
}
