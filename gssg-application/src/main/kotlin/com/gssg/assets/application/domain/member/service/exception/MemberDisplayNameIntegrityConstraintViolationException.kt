package com.gssg.assets.application.domain.member.service.exception

import com.gssg.assets.domain.member.MemberDisplayName

/**
 * @Author Heli
 */
class MemberDisplayNameIntegrityConstraintViolationException(
    override val message: String?
) : RuntimeException() {
    constructor(memberDisplayName: MemberDisplayName)
            : this("Member.DisplayName 무결성 제약조건에 위배됩니다.[displayName=${memberDisplayName.displayName}]")
}
