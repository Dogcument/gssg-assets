package com.gssg.assets.persistence.domain.member.repository

import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberEmail
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import com.gssg.assets.persistence.common.CommonDefinition
import com.gssg.assets.persistence.domain.member.entity.MemberEntity

/**
 * @Author Heli
 */
interface MemberRepository {

    fun insert(memberDefinition: MemberDefinition)

    fun update(id: Long, memberDefinition: MemberDefinition)

    fun findById(id: Long): MemberEntity?

    fun findByDisplayName(memberDisplayName: MemberDisplayName): MemberEntity?

    fun findByEmail(memberEmail: MemberEmail): MemberEntity?

    data class MemberDefinition(
        val email: String,
        val password: String,
        val displayName: String,
        val introduce: String,
        val profileDog: ProfileDogType,
        val role: Role,
        val status: Status
    ) : CommonDefinition
}
