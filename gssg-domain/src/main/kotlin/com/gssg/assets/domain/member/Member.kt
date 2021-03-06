package com.gssg.assets.domain.member

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Member(
    override val id: MemberId = MemberId(-1L),
    val createdAt: MemberCreatedAt = MemberCreatedAt(LocalDateTime.MIN),
    val modifiedAt: MemberModifiedAt = MemberModifiedAt(LocalDateTime.MIN),
    val email: MemberEmail,
    val password: MemberPassword,
    val displayName: MemberDisplayName,
    val introduce: MemberIntroduce,
    val profileDog: MemberProfileDog,
    val role: MemberRole,
    val status: MemberStatus
) : BaseDomain() {

    companion object {
        fun create(
            newEmail: MemberEmail,
            newPassword: MemberPassword,
            newDisplayName: MemberDisplayName,
            newIntroduce: MemberIntroduce
        ) = Member(
            email = newEmail,
            password = newPassword,
            displayName = newDisplayName,
            introduce = newIntroduce,
            profileDog = MemberProfileDog.default(),
            role = MemberRole(role = Role.USER),
            status = MemberStatus(status = Status.ACTIVE)
        )
    }

    fun update(
        newDisplayName: MemberDisplayName?,
        newIntroduce: MemberIntroduce?,
        newProfileDog: MemberProfileDog?
    ): Member {
        val displayName = newDisplayName ?: displayName
        val introduce = newIntroduce ?: introduce
        val profileDog = newProfileDog ?: profileDog
        return Member(
            id = id,
            email = email,
            password = password,
            displayName = displayName,
            introduce = introduce,
            profileDog = profileDog,
            role = role,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            status = status
        )
    }

    fun delete(): Member {
        return Member(
            id = id,
            email = email,
            password = password,
            displayName = displayName,
            introduce = introduce,
            profileDog = profileDog,
            role = role,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            status = MemberStatus(status = Status.INACTIVE)
        )
    }
}
