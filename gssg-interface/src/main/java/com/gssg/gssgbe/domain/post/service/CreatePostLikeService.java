package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.entity.PostLike;
import com.gssg.gssgbe.domain.post.repository.PostLikeRepository;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CreatePostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public long create(final long postId, final Member loginMember) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        return postLikeRepository.save(new PostLike(post, loginMember)).getId();
    }
}
