package com.gssg.assets.application.domain.member.service.validation

import com.gssg.assets.domain.member.Member

/**
 * @Author Heli
 */
interface MemberValidator {
    fun requireValid(member: Member)
}
