package com.gssg.assets.persistence.domain.member.repository

import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.persistence.domain.member.entity.Member

/**
 * @Author Heli
 */
interface MemberRepository {
    fun find(memberId: MemberId): Member?
}
