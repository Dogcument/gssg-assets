package com.gssg.gssgbe.domain.subject.service;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import com.gssg.gssgbe.domain.subject.repository.SubjectOfDateRepository;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindSubjectOfDateService {

    private final SubjectOfDateRepository subjectOfDateRepository;

    public Optional<SubjectDto> findByDate(LocalDate localDate) {
        return subjectOfDateRepository.findByDate(localDate)
            .map(SubjectDto::new);
    }
}
