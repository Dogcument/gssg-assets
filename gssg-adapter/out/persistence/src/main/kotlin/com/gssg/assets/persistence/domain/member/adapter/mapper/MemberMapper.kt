package com.gssg.assets.persistence.domain.member.adapter.mapper

import com.gssg.assets.domain.member.*
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import com.gssg.assets.persistence.domain.member.entity.Member
import com.gssg.assets.domain.member.Member as CoreMember

/**
 * @Author Heli
 */
object MemberMapper {

    fun toApplication(member: Member): CoreMember {
        return CoreMember(
            id = MemberId(id = member.id.value),
            createdAt = MemberCreatedAt(createdAt = member.createdAt),
            modifiedAt = MemberModifiedAt(modifiedAt = member.modifiedAt),
            email = MemberEmail(email = member.email),
            password = MemberPassword(password = member.password),
            displayName = MemberDisplayName(displayName = member.displayName),
            introduce = MemberIntroduce(introduce = member.introduce),
            profileDog = MemberProfileDog(profileDog = ProfileDogType.of(member.profileDog)),
            role = MemberRole(role = Role.of(member.role)),
            status = MemberStatus(status = Status.of(member.status))
        )
    }
}
