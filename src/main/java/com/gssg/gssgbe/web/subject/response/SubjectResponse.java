package com.gssg.gssgbe.web.subject.response;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SubjectResponse {

    @Schema(description = "이름")
    private final String name;

    @Schema(description = "설명")
    private final String description;

    public SubjectResponse(SubjectDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
    }
}
