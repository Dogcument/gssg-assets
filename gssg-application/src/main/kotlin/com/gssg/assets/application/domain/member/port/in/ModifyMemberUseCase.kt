package com.gssg.assets.application.domain.member.port.`in`

import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.domain.member.MemberIntroduce
import com.gssg.assets.domain.member.MemberProfileDog

/**
 * @Author Heli
 */
interface ModifyMemberUseCase {

    fun command(command: Command)

    data class Command(
        val memberId: MemberId,
        val memberDisplayName: MemberDisplayName? = null,
        val memberIntroduce: MemberIntroduce? = null,
        val memberProfileDog: MemberProfileDog? = null,
    )
}
