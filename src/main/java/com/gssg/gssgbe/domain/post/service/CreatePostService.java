package com.gssg.gssgbe.domain.post.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.post.dto.request.CreatePostRequestDto;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostService {

    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository;

    public long create(CreatePostRequestDto request) {
        Subject subject = subjectRepository.findByName(request.getSubjectName())
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXISTS_SUBJECT));
        Post post = new Post(request.getWriter(), subject, request.getContent());

        return postRepository.save(post).getId();
    }
}
