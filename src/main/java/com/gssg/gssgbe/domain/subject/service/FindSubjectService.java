package com.gssg.gssgbe.domain.subject.service;

import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindSubjectService {

    private final SubjectRepository subjectRepository;

    public Optional<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }
}
