package com.gssg.gssgbe.domain.subject.dto;

import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubjectOfDateDto {

    private final Long id;
    private final SubjectDto subject;
    private final LocalDate date;

    public static SubjectOfDateDto of(final SubjectOfDate entity) {
        return SubjectOfDateDto.builder()
            .id(entity.getId())
            .subject(SubjectDto.of(entity.getSubject()))
            .date(entity.getDate())
            .build();
    }
}
