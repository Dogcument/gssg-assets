package com.gssg.gssgbe.web.subject;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.subject.service.FindSubjectOfDateService;
import com.gssg.gssgbe.web.subject.response.SubjectResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "글감")
@RequiredArgsConstructor
@RestController
public class FindSubjectController {

    private final FindSubjectOfDateService findSubjectOfDateService;

    @Operation(summary = "그날의 글감 조회")
    @GetMapping("/api/v1/sujects/date/{targetDate}")
    public SubjectResponse findByDate(
        @PathVariable LocalDate targetDate) {
        return findSubjectOfDateService.findByDate(targetDate)
            .map(SubjectResponse::new)
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
    }
}
