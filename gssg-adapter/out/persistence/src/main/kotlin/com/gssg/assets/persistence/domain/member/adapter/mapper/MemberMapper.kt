package com.gssg.assets.persistence.domain.member.adapter.mapper

import com.gssg.assets.domain.logger
import com.gssg.assets.domain.member.*
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import com.gssg.assets.persistence.domain.member.entity.MemberEntity
import com.gssg.assets.persistence.domain.member.repository.MemberRepository

/**
 * @Author Heli
 */
object MemberMapper {

    private val logger = logger()

    fun toDefinition(member: Member): MemberRepository.MemberDefinition {
        logger.info("어댑터 모듈의 매퍼에서 멤버 객체를 엔티티 정의로 변경")
        return MemberRepository.MemberDefinition(
            email = member.email.email,
            password = member.password.password,
            displayName = member.displayName.displayName,
            introduce = member.introduce.introduce,
            profileDog = member.profileDog.profileDog,
            role = member.role.role,
            status = member.status.status
        )
    }

    fun toApplication(memberEntity: MemberEntity): Member {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 멤버 객체로 변경")
        return Member(
            id = MemberId(id = memberEntity.id.value),
            createdAt = MemberCreatedAt(createdAt = memberEntity.createdAt),
            modifiedAt = MemberModifiedAt(modifiedAt = memberEntity.modifiedAt),
            email = MemberEmail(email = memberEntity.email),
            password = MemberPassword(password = memberEntity.password),
            displayName = MemberDisplayName(displayName = memberEntity.displayName),
            introduce = MemberIntroduce(introduce = memberEntity.introduce),
            profileDog = MemberProfileDog(profileDog = ProfileDogType.of(memberEntity.profileDog)),
            role = MemberRole(role = Role.of(memberEntity.role)),
            status = MemberStatus(status = Status.of(memberEntity.status))
        )
    }
}
