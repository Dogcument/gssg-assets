package com.gssg.assets.application.port.out

import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId

/**
 * @Author Heli
 */
interface MemberPersistencePort {

    fun findById(memberId: MemberId): Member?

    fun findByDisplayName(memberDisplayName: MemberDisplayName): Member?

    fun existDisplayName(memberDisplayName: MemberDisplayName): Boolean
}
