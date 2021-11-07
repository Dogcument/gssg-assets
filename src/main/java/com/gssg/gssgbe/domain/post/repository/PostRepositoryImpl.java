package com.gssg.gssgbe.domain.post.repository;

import static com.gssg.gssgbe.domain.post.entity.QPost.*;
import static com.gssg.gssgbe.domain.post.repository.PostRepositoryImpl.SortType.*;
import static com.querydsl.core.types.Order.*;

import java.util.List;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Post> findAll(final NoOffsetPageRequest pageRequest) {
		final JPAQuery<Post> jpaQuery = queryFactory
			.selectFrom(post)
			.limit(pageRequest.getSize());

		if (pageRequest.isSorted()) {
			return jpaQuery
				.orderBy(LIKE_COUNT.orderSpecifier)
				.fetch();
		}

		return jpaQuery
			.where(
				post.id.lt(pageRequest.getCurrent())
			)
			.orderBy(ID.orderSpecifier)
			.fetch();
	}

	@Override
	public List<Post> findAllByMember(final Member member, final NoOffsetPageRequest pageRequest) {
		final JPAQuery<Post> jpaQuery = queryFactory
			.selectFrom(post)
			.where(
				post.member.eq(member),
				post.id.lt(pageRequest.getCurrent())
			)
			.orderBy(post.id.desc())
			.limit(pageRequest.getSize());

		return jpaQuery.fetch();
	}

	@Getter
	@RequiredArgsConstructor
	public enum SortType {
		ID(new OrderSpecifier<>(DESC, post.id)),
		LIKE_COUNT(new OrderSpecifier<>(DESC, post.postLikes.size())),
		;

		private final OrderSpecifier<?> orderSpecifier;
	}
}
