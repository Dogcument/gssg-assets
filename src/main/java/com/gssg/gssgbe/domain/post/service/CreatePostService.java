package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.post.dto.request.CreatePostRequestDto;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.service.FindSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostService {

    private final FindSubjectService findSubjectService;

    private final PostRepository postRepository;

    public long create(CreatePostRequestDto request) {
        Subject subject = findSubjectService.findByName(request.getSubjectName())
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        Post post = new Post(request.getWriter(), subject, request.getContent());

        return postRepository.save(post).getId();
    }
}
