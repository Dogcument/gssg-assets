package com.gssg.gssgbe.domain.subject.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import com.gssg.gssgbe.domain.subject.repository.SubjectOfDateRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindSubjectOfDateService {

	private final SubjectOfDateRepository subjectOfDateRepository;

	public Optional<SubjectDto> findByDate(final LocalDate localDate) {
		return subjectOfDateRepository.findByDate(localDate)
			.map(SubjectDto::new);
	}
}
