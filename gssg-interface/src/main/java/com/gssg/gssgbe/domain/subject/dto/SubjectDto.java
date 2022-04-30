package com.gssg.gssgbe.domain.subject.dto;

import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubjectDto {

    private final Long id;
    private final String name;
    private final String description;

    public static SubjectDto of(final Subject entity) {
        return SubjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public static SubjectDto of(final SubjectOfDate entity) {
        return of(entity.getSubject());
    }
}
