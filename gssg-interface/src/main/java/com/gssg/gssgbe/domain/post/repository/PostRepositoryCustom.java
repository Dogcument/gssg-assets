package com.gssg.gssgbe.domain.post.repository;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.entity.Post;
import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findAll(final NoOffsetPageRequest pageRequest);

    List<Post> findAllByMember(final Member member, final NoOffsetPageRequest pageable);
}
