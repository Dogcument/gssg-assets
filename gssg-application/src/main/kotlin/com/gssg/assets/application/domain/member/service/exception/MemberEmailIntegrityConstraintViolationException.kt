package com.gssg.assets.application.domain.member.service.exception

import com.gssg.assets.domain.member.MemberEmail

/**
 * @Author Heli
 */
class MemberEmailIntegrityConstraintViolationException(
    override val message: String?
) : RuntimeException() {
    constructor(memberEmail: MemberEmail)
            : this("Member.Email 무결성 제약조건에 위배됩니다.[email=${memberEmail.email}]")
}
