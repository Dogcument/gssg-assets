package com.gssg.assets.persistence.domain.member.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class Member(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Member>(Members)

    val createdAt by Members.createdAt
    val modifiedAt by Members.modifiedAt
    val email by Members.email
    val password by Members.password
    val displayName by Members.displayName
    val introduce by Members.introduce
    val profileDog by Members.profileDog
    val role by Members.role
    val status by Members.status
}
