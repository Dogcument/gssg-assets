package com.gssg.gssgbe.domain.subject.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.subject.dto.SubjectDto;
import com.gssg.gssgbe.util.TestMemberInit;
import com.gssg.gssgbe.util.TestPostInit;
import com.gssg.gssgbe.util.TestReplyInit;
import com.gssg.gssgbe.util.TestSubjectInit;

@Transactional
@Import({TestMemberInit.class, TestSubjectInit.class, TestPostInit.class, TestReplyInit.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FindSubjectServiceTest {

	@Autowired
	private FindSubjectService findSubjectService;

	@Test
	void findAll() {
		// given
		final PageRequest pageRequest = PageRequest.of(0, 5);

		// when
		final Page<SubjectDto> subjects = findSubjectService.findAll(pageRequest);

		// then
		assertThat(subjects).hasSize(3);
	}
}
