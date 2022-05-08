package com.gssg.assets.application.domain.member.port.out

import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId

/**
 * @Author Heli
 */
interface MemberPersistencePort {

    fun insert(member: Member)

    fun update(member: Member)

    fun findById(memberId: MemberId): Member?

    fun findByDisplayName(memberDisplayName: MemberDisplayName): Member?

    fun existBy(memberDisplayName: MemberDisplayName): Boolean
}
