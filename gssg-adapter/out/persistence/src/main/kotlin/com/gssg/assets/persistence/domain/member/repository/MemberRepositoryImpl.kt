package com.gssg.assets.persistence.domain.member.repository

import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.persistence.domain.member.entity.Member
import com.gssg.assets.persistence.domain.member.entity.Members.displayName

/**
 * @Author Heli
 */
class MemberRepositoryImpl : MemberRepository {

    override fun findById(memberId: MemberId): Member? {
        return Member.findById(memberId.id)
    }

    override fun findByDisplayName(memberDisplayName: MemberDisplayName): Member? {
        return Member.find { displayName eq memberDisplayName.displayName }.singleOrNull()
    }
}
