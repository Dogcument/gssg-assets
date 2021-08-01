package com.gssg.gssgbe.web.post.response;

import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import com.gssg.gssgbe.web.subject.response.SubjectResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostResponse {

    @Schema(description = "글 PK")
    private final Long id;

    @Schema(description = "내용")
    private final SubjectResponse subject;

    @Schema(description = "내용")
    private final String content;

    @Schema(description = "작성자")
    private final MemberResponse member;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PostResponse(PostDto dto) {
        this.id = dto.getId();
        this.subject = new SubjectResponse(dto.getSubjectDto());
        this.content = dto.getContent();
        this.member = new MemberResponse(dto.getMemberDto());
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }
}
