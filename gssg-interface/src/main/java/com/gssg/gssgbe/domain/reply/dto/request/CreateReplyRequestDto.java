package com.gssg.gssgbe.domain.reply.dto.request;

import com.gssg.gssgbe.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CreateReplyRequestDto {

    private final Member member;
    private final Long postId;
    private final String content;
}
