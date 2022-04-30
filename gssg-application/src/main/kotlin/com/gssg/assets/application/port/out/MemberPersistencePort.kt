package com.gssg.assets.application.port.out

import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberId

/**
 * @Author Heli
 */
interface MemberPersistencePort {

    fun find(memberId: MemberId): Member?
}
