package com.gssg.assets.application.domain.member.service.validation

import com.gssg.assets.application.domain.member.port.`in`.QueryMemberUseCase
import com.gssg.assets.application.domain.member.service.exception.MemberDisplayNameIntegrityConstraintViolationException
import com.gssg.assets.application.domain.member.service.exception.MemberEmailIntegrityConstraintViolationException
import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberEmail
import com.gssg.assets.domain.member.MemberId
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class CommandMemberValidator(
    private val queryMemberUseCase: QueryMemberUseCase
) : MemberValidator {

    override fun requireValid(member: Member) {
        when {
            exist(
                memberDisplayName = member.displayName,
                memberId = member.id
            ) -> throw MemberDisplayNameIntegrityConstraintViolationException(
                member.displayName
            )
            exist(
                memberEmail = member.email,
                memberId = member.id
            ) -> throw MemberEmailIntegrityConstraintViolationException(
                member.email
            )
        }
    }

    private fun exist(memberDisplayName: MemberDisplayName, memberId: MemberId): Boolean {
        val byDisplayNameQuery = QueryMemberUseCase.Query.ByDisplayName(
            memberDisplayName = memberDisplayName,
            memberId = memberId
        )
        return queryMemberUseCase.existBy(query = byDisplayNameQuery)
    }

    private fun exist(memberEmail: MemberEmail, memberId: MemberId): Boolean {
        val byEmailQuery = QueryMemberUseCase.Query.ByEmail(
            memberEmail = memberEmail,
            memberId = memberId
        )
        return queryMemberUseCase.existBy(query = byEmailQuery)
    }
}
