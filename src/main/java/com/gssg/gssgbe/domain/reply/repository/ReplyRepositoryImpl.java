package com.gssg.gssgbe.domain.reply.repository;

import static com.gssg.gssgbe.common.util.QuerydslUtil.*;
import static com.gssg.gssgbe.domain.reply.entity.QReply.*;
import static com.querydsl.core.types.Order.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<Reply> findAllSlice(final Pageable pageable) {
		final JPAQuery<Reply> jpaQuery = queryFactory
			.selectFrom(reply)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(getAllOrderSpecifiers(pageable.getSort()).toArray(OrderSpecifier[]::new));

		return createSlice(jpaQuery.fetch(), pageable);
	}

	@Override
	public Slice<Reply> findAllByPostId(final long postId, final Pageable pageable) {
		final JPAQuery<Reply> jpaQuery = queryFactory
			.selectFrom(reply)
			.where(reply.post.id.eq(postId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(getAllOrderSpecifiers(pageable.getSort()).toArray(OrderSpecifier[]::new));

		return createSlice(jpaQuery.fetch(), pageable);
	}

	private List<OrderSpecifier<?>> getAllOrderSpecifiers(final Sort sort) {
		return Stream.concat(
				sort.stream()
					.map(order -> SortType.findBy(order.getProperty()).getOrderSpecifier()),
				Stream.of(SortType.getDefault().getOrderSpecifier()))
			.collect(Collectors.toUnmodifiableList());
	}

	@Getter
	@RequiredArgsConstructor
	public enum SortType {
		CREATE_AT(new OrderSpecifier<>(DESC, reply.createdAt)),
		LIKE_COUNT(new OrderSpecifier<>(DESC, reply.replyLikes.size())),
		;

		private final OrderSpecifier<?> orderSpecifier;

		public static SortType findBy(final String name) {
			return Arrays.stream(SortType.values()).filter(type -> type.name().equals(name))
				.findFirst()
				.orElse(CREATE_AT);
		}

		public static SortType getDefault() {
			return CREATE_AT;
		}
	}
}
