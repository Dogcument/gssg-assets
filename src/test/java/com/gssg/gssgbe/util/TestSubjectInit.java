package com.gssg.gssgbe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;

@TestConfiguration
public class TestSubjectInit {

	@Autowired private SubjectRepository subjectRepository;

	@Bean
	public void testSubjectInit() {
		subjectRepository.save(new Subject("강남역", "2호선"));
		subjectRepository.save(new Subject("야탑역", "분당선"));
		subjectRepository.save(new Subject("신논현역", "9호선"));
	}
}
