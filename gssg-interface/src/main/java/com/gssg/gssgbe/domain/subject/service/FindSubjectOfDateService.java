package com.gssg.gssgbe.domain.subject.service;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import com.gssg.gssgbe.domain.subject.dto.SubjectOfDateDto;
import com.gssg.gssgbe.domain.subject.repository.SubjectOfDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindSubjectOfDateService {

    private final SubjectOfDateRepository subjectOfDateRepository;

    public Optional<SubjectDto> findByDate(final LocalDate localDate) {
        return subjectOfDateRepository.findByDate(localDate)
                .map(SubjectDto::of);
    }

    public List<SubjectOfDateDto> findAllByDateBetween(final LocalDate from, final LocalDate to) {
        return subjectOfDateRepository.findAllByDateBetween(from, to).stream()
                .map(SubjectOfDateDto::of)
                .collect(Collectors.toList());
    }
}
