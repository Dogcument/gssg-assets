package com.gssg.gssgbe.domain.subject.dto;

import java.time.LocalDate;

import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;

import lombok.Getter;

@Getter
public class SubjectOfDateDto {

	private final Long id;
	private final SubjectDto subject;
	private final LocalDate date;

	public SubjectOfDateDto(final SubjectOfDate entity) {
		this.id = entity.getId();
		this.subject = new SubjectDto(entity.getSubject());
		this.date = entity.getDate();
	}
}
