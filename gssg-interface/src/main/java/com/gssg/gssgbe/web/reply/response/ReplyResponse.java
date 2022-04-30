package com.gssg.gssgbe.web.reply.response;

import com.gssg.gssgbe.domain.reply.dto.response.ReplyDto;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyResponse {

    @Schema(description = "댓글 id")
    private final Long id;

    @Schema(description = "작성자")
    private final MemberResponse member;

    @Schema(description = "내용")
    private final String content;

    @Schema(description = "좋아요")
    private final Boolean like;

    @Schema(description = "좋아요 수")
    private final Long likeCount;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static ReplyResponse of(final ReplyDto dto) {
        return ReplyResponse.builder()
            .id(dto.getId())
            .member(new MemberResponse(dto.getMemberDto()))
            .content(dto.getContent())
            .like(dto.getLike())
            .likeCount(dto.getLikeCount())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())
            .build();
    }
}
