package com.gssg.gssgbe.domain.subject.dto;

import com.gssg.gssgbe.domain.subject.entity.Subject;
import lombok.Getter;

@Getter
public class SubjectDto {

    private final Long id;
    private final String name;
    private final String description;

    public SubjectDto(Subject entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }
}
