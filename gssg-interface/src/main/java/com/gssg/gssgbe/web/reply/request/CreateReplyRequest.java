package com.gssg.gssgbe.web.reply.request;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.dto.request.CreateReplyRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReplyRequest {

    @Schema(description = "글 id")
    @NotNull
    private Long postId;

    @Schema(description = "글 내용")
    @Length(max = 255)
    @NotEmpty
    private String content;

    public CreateReplyRequestDto toDto(final Member member) {
        return CreateReplyRequestDto.of(member, postId, content);
    }
}
