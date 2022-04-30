package com.gssg.gssgbe.domain.reply.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.reply.dto.request.CreateReplyRequestDto;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.gssg.gssgbe.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public long create(final CreateReplyRequestDto request) {
        final Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXISTS_SUBJECT));
        final Reply reply = new Reply(request.getMember(), post, request.getContent());

        return replyRepository.save(reply).getId();
    }
}
