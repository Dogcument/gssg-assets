package com.gssg.gssgbe.web.member.response;

import java.util.Collections;
import java.util.List;

public class FindAllMemberResponse {

    private final List<MemberResponse> memberRespons;

    public FindAllMemberResponse(final List<MemberResponse> memberRespons) {
        this.memberRespons = memberRespons;
    }

    public List<MemberResponse> getMemberRespons() {
        return Collections.unmodifiableList(memberRespons);
    }
}
