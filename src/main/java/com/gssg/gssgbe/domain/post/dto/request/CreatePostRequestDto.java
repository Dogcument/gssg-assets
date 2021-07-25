package com.gssg.gssgbe.domain.post.dto.request;

import com.gssg.gssgbe.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostRequestDto {

    private final Member writer;
    private final String subjectName;
    private final String content;
}
