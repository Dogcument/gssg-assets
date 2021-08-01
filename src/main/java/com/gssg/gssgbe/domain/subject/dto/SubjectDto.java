package com.gssg.gssgbe.domain.subject.dto;

import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;
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

    public SubjectDto(SubjectOfDate entity) {
        Subject subject = entity.getSubject();

        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
    }
}
