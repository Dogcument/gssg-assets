package com.gssg.gssgbe.web.member;

import com.gssg.gssgbe.domain.member.service.CreateMemberService;
import com.gssg.gssgbe.web.member.request.CreateMemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@Validated
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final CreateMemberService createMemberService;

    @Operation(summary = "회원 가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/members")
    public Long create(@RequestBody CreateMemberRequest request) {
        return createMemberService.create(request.toDto());
    }
}
