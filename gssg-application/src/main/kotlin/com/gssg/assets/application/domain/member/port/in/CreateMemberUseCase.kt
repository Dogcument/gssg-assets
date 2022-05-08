package com.gssg.assets.application.domain.member.port.`in`

import com.gssg.assets.domain.member.Member

/**
 * @Author Heli
 */
interface CreateMemberUseCase {

    fun command(command: Command)

    data class Command(
        val member: Member
    )
}
