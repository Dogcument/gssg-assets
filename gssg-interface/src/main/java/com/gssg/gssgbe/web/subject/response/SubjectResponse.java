package com.gssg.gssgbe.web.subject.response;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubjectResponse {

    @Schema(description = "이름")
    private final String name;

    @Schema(description = "설명")
    private final String description;

    public static SubjectResponse of(final SubjectDto dto) {
        return SubjectResponse.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .build();
    }
}
