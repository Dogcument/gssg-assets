package com.gssg.gssgbe.domain.subject.dto;

import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SubjectOfDateDto {

    private final Long id;
    private final SubjectDto subject;
    private final LocalDate date;

    public SubjectOfDateDto(SubjectOfDate entity) {
        this.id = entity.getId();
        this.subject = new SubjectDto(entity.getSubject());
        this.date = entity.getDate();
    }
}
