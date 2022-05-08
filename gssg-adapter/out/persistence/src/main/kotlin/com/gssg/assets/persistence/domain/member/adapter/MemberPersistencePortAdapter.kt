package com.gssg.assets.persistence.domain.member.adapter

import com.gssg.assets.application.domain.member.port.out.MemberPersistencePort
import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberEmail
import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.persistence.domain.member.adapter.mapper.MemberMapper
import com.gssg.assets.persistence.domain.member.repository.MemberRepository

/**
 * @Author Heli
 */
class MemberPersistencePortAdapter(
    private val memberRepository: MemberRepository
) : MemberPersistencePort {

    override fun insert(member: Member) {
        val memberDefinition = MemberMapper.toDefinition(member = member)
        memberRepository.insert(memberDefinition = memberDefinition)
    }

    override fun update(member: Member) {
        val memberDefinition = MemberMapper.toDefinition(member = member)
        memberRepository.update(id = member.longId, memberDefinition = memberDefinition)
    }

    override fun findById(memberId: MemberId): Member? {
        val memberEntity = memberRepository.findById(id = memberId.id) ?: return null
        return MemberMapper.toApplication(memberEntity = memberEntity)
    }

    override fun findByDisplayName(memberDisplayName: MemberDisplayName): Member? {
        val memberEntity = memberRepository.findByDisplayName(
            memberDisplayName = memberDisplayName
        ) ?: return null
        return MemberMapper.toApplication(memberEntity = memberEntity)
    }

    override fun findByEmail(memberEmail: MemberEmail): Member? {
        val memberEntity = memberRepository.findByEmail(
            memberEmail = memberEmail
        ) ?: return null
        return MemberMapper.toApplication(memberEntity = memberEntity)
    }
}
