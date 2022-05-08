package com.gssg.assets.persistence.domain.member.repository

import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities.displayName
import com.gssg.assets.persistence.domain.member.entity.MemberEntity
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * @Author Heli
 */
class MemberRepositoryImpl : MemberRepository,
    CommonRepository<Long, MemberEntity, MemberEntities>(MemberEntities) {

    override fun insert(memberDefinition: MemberRepository.MemberDefinition) {
        execInsert {
            insertOrUpdate(it, memberDefinition)
        }
    }

    override fun update(id: Long, memberDefinition: MemberRepository.MemberDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, memberDefinition)
        }
    }

    private fun MemberEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        memberDefinition: MemberRepository.MemberDefinition
    ) {
        it[email] = memberDefinition.email
        it[password] = memberDefinition.password
        it[displayName] = memberDefinition.displayName
        it[introduce] = memberDefinition.introduce
        it[profileDog] = memberDefinition.profileDog.name
        it[role] = memberDefinition.role.name
        it[status] = memberDefinition.status.name
    }

    override fun findById(id: Long): MemberEntity? {
        return queryById(id = id)
    }

    override fun findByDisplayName(memberDisplayName: MemberDisplayName): MemberEntity? {
        return MemberEntity.find { displayName eq memberDisplayName.displayName }.singleOrNull()
    }
}
