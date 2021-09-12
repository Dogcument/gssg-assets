package com.gssg.gssgbe.web.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import com.gssg.gssgbe.web.member.response.FindAllMemberResponse;
import com.gssg.gssgbe.web.member.response.MemberResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원")
@RequiredArgsConstructor
@RestController
public class FindMemberController {

	private final FindMemberService findMemberService;

	@Operation(summary = "전체 조회")
	@GetMapping("/api/v1/members")
	public FindAllMemberResponse findAll() {
		final List<MemberDto> dtos = findMemberService.findAll();

		return new FindAllMemberResponse(dtos.stream()
			.map(MemberResponse::new)
			.collect(Collectors.toList()));
	}
}
