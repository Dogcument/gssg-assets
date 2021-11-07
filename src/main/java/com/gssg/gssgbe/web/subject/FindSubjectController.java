package com.gssg.gssgbe.web.subject;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.domain.subject.service.FindSubjectOfDateService;
import com.gssg.gssgbe.web.subject.response.SubjectOfDateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "글감")
@RequiredArgsConstructor
@RestController
public class FindSubjectController {

	private final FindSubjectOfDateService findSubjectOfDateService;

	@Operation(summary = "그날의 글감 조회", description = "조회 날짜에 글감이 없을 수도 있습니다.")
	@GetMapping("/api/v1/subjects")
	public List<SubjectOfDateResponse> findByDateRange(
		@RequestParam final LocalDate from,
		@RequestParam final LocalDate to) {
		return findSubjectOfDateService.findAllByDateBetween(from, to).stream()
			.map(SubjectOfDateResponse::of)
			.collect(Collectors.toList());
	}
}
