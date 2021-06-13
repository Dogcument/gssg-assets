package com.gssg.gssgbe.controller.member;

import com.gssg.gssgbe.domain.member.dto.response.FindAllMemberResponse;
import com.gssg.gssgbe.domain.member.dto.response.MemberResponse;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@RequiredArgsConstructor
@RestController
public class FindMemberController {

  private final FindMemberService findMemberService;

  @Operation(summary = "전체 조회")
  @GetMapping("/api/v1/members")
  public FindAllMemberResponse findAll() {
    List<MemberResponse> memberRespons = findMemberService.findAll();

    return new FindAllMemberResponse(memberRespons);
  }
}
