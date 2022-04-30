package com.gssg.assets.persistence.domain.member.repository

import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.persistence.domain.member.entity.Member

/**
 * @Author Heli
 */
class MemberRepositoryImpl : MemberRepository {
    override fun find(memberId: MemberId): Member? {
        return Member.findById(memberId.id)
    }
}
