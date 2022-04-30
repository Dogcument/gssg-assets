package com.gssg.gssgbe.web.subject.response;

import com.gssg.gssgbe.domain.subject.dto.SubjectOfDateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SubjectOfDateResponse {

    @Schema(description = "이름")
    private final LocalDate date;

    @Schema(description = "설명")
    private final SubjectResponse subject;

    public static SubjectOfDateResponse of(final SubjectOfDateDto dto) {
        return SubjectOfDateResponse.builder()
                .date(dto.getDate())
                .subject(SubjectResponse.of(dto.getSubject()))
                .build();
    }
}
