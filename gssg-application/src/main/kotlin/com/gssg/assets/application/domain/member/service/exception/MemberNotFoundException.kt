package com.gssg.assets.application.domain.member.service.exception

import com.gssg.assets.domain.member.MemberId

/**
 * @Author Heli
 */
class MemberNotFoundException(
    override val message: String?
) : RuntimeException() {
    constructor(memberId: MemberId) : this("멤버를 찾지 못했습니다 [memberId=${memberId.id}]")
}
