package com.gssg.assets.persistence.domain.member.adapter

import com.gssg.assets.application.port.out.MemberPersistencePort
import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.persistence.domain.member.adapter.mapper.MemberMapper
import com.gssg.assets.persistence.domain.member.repository.MemberRepository

/**
 * @Author Heli
 */
class MemberPersistencePortAdapter(
    private val memberRepository: MemberRepository
) : MemberPersistencePort {

    override fun findById(memberId: MemberId): Member? {
        val member = memberRepository.findById(memberId = memberId) ?: return null
        return MemberMapper.toApplication(member)
    }

    override fun findByDisplayName(memberDisplayName: MemberDisplayName): Member? {
        val member = memberRepository.findByDisplayName(
            memberDisplayName = memberDisplayName
        ) ?: return null
        return MemberMapper.toApplication(member)
    }

    override fun existDisplayName(memberDisplayName: MemberDisplayName): Boolean {
        val member = memberRepository.findByDisplayName(
            memberDisplayName = memberDisplayName
        )
        return member != null
    }
}
