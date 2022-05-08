package com.gssg.assets.application.domain.member.port.`in`

import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId

/**
 * @Author Heli
 */
interface QueryMemberUseCase {

    fun queryById(query: Query.ById): Result?
    fun queryByDisplayName(query: Query.ByDisplayName): Result?
    fun existBy(query: Query.ByDisplayName): Boolean

    sealed class Query {
        data class ById(
            val memberId: MemberId
        ) : Query()

        data class ByDisplayName(
            val memberDisplayName: MemberDisplayName
        ) : Query()
    }

    data class Result(
        val member: Member
    )
}
