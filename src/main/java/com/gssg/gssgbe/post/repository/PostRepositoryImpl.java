package com.gssg.gssgbe.post.repository;

import static com.gssg.gssgbe.post.entity.QPost.post;

import com.gssg.gssgbe.common.util.QuerydslUtil;
import com.gssg.gssgbe.post.entity.Post;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Slice<Post> findAllSlice(Pageable pageable) {
    JPAQuery<Post> jpaQuery = queryFactory
        .selectFrom(post)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());

    List<Post> content = jpaQuery.fetch();

    return QuerydslUtil.createSlice(content, pageable);
  }
}
