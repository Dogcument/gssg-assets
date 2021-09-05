package com.gssg.gssgbe.web.member;

import javax.validation.constraints.Email;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.domain.member.service.CreateMemberService;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import com.gssg.gssgbe.web.member.request.CreateMemberRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원")
@Validated
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final CreateMemberService createMemberService;
	private final FindMemberService findMemberService;

	@Operation(summary = "회원 이메일 존재 여부")
	@GetMapping("/api/v1/members/email/exists")
	public Boolean existsEmail(@RequestParam @Email final String email) {
		return findMemberService.existsEmail(email);
	}

	@Operation(summary = "회원 필명 존재 여부")
	@GetMapping("/api/v1/members/nickname/exists")
	public Boolean existsNickname(@RequestParam final String nickname) {
		return findMemberService.existsNickname(nickname);
	}

	@Operation(summary = "회원 가입")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/members")
	public Long create(@RequestBody final CreateMemberRequest request) {
		return createMemberService.create(request.toDto());
	}
}
