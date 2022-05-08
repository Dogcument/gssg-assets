package com.gssg.assets.application.domain.member.port.`in`

import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberEmail
import com.gssg.assets.domain.member.MemberId

/**
 * @Author Heli
 */
interface QueryMemberUseCase {

    fun queryById(query: Query.ById): Result?
    fun queryByDisplayName(query: Query.ByDisplayName): Result?
    fun existBy(query: Query.ByDisplayName): Boolean
    fun existBy(query: Query.ByEmail): Boolean

    sealed class Query {
        abstract val memberId: MemberId?

        data class ById(
            override val memberId: MemberId
        ) : Query()

        data class ByDisplayName(
            val memberDisplayName: MemberDisplayName,
            override val memberId: MemberId? = null
        ) : Query()

        data class ByEmail(
            val memberEmail: MemberEmail,
            override val memberId: MemberId? = null
        ) : Query()
    }

    data class Result(
        val member: Member
    )
}
