package com.gssg.gssgbe.domain.subject.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FindSubjectServiceTest {

    @Autowired
    private FindSubjectService findSubjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void findAll() {
        // given
        Subject subject1 = new Subject("sub1", "sub1 description");
        Subject subject2 = new Subject("sub2", "sub2 description");

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        PageRequest pageRequest = PageRequest.of(0, 5);

        // when
        Page<SubjectDto> subjects = findSubjectService.PagefindAll(pageRequest);

        // then
        assertThat(subjects).hasSize(2);
    }
}
