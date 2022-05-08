package com.gssg.assets.application.domain.member.port.`in`

import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberId

/**
 * @Author Heli
 */
interface QueryMemberUseCase {

    fun query(query: Query): Result?

    data class Query(
        val memberId: MemberId
    )

    data class Result(
        val member: Member
    )
}
