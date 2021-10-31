package com.gssg.gssgbe.domain.subject.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindSubjectService {

	private final SubjectRepository subjectRepository;

	public Optional<SubjectDto> findByName(final String name) {
		return subjectRepository.findByName(name)
			.map(SubjectDto::new);
	}

	public Page<SubjectDto> findAll(final Pageable pageable) {
		return subjectRepository.findAll(pageable)
			.map(SubjectDto::new);
	}
}
