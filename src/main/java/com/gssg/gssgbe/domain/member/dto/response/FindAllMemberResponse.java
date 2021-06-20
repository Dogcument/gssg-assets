package com.gssg.gssgbe.domain.member.dto.response;

import java.util.Collections;
import java.util.List;

public class FindAllMemberResponse {

  private final List<MemberResponse> memberRespons;

  public FindAllMemberResponse(List<MemberResponse> memberRespons) {
    this.memberRespons = memberRespons;
  }

  public List<MemberResponse> getMemberRespons() {
    return Collections.unmodifiableList(memberRespons);
  }
}
