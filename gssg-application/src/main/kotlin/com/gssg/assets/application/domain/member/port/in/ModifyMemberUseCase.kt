package com.gssg.assets.application.domain.member.port.`in`

import com.gssg.assets.domain.member.*

/**
 * @Author Heli
 */
interface ModifyMemberUseCase {

    fun command(command: Command)

    data class Command(
        val memberId: MemberId,
        val memberEmail: MemberEmail? = null,
        val memberPassword: MemberPassword? = null,
        val memberDisplayName: MemberDisplayName? = null,
        val memberIntroduce: MemberIntroduce? = null,
        val memberProfileDog: MemberProfileDog? = null,
        val memberRole: MemberRole? = null,
        val memberStatus: MemberStatus? = null
    )
}
