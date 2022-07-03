package com.gssg.assets.persistence.domain.member.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class MemberEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MemberEntity>(MemberEntities)

    val createdAt by MemberEntities.createdAt
    val modifiedAt by MemberEntities.modifiedAt
    val email by MemberEntities.email
    val password by MemberEntities.password
    val displayName by MemberEntities.displayName
    val introduce by MemberEntities.introduce
    val profileDog by MemberEntities.profileDog
    val role by MemberEntities.role
    val status by MemberEntities.status
}
