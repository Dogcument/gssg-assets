package com.gssg.assets.persistence.domain.member.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

/**
 * @Author Heli
 */
object Members : LongIdTable(name = "member") {
    val createdAt = datetime("createdAt")
    val modifiedAt = datetime("modifiedAt")
    val email = varchar("email", 50)
    val password = varchar("password", 128)
    val displayName = varchar("displayName", 20)
    val introduce = varchar("introduce", 32)
    val profileDog = varchar("profileDog", 32)
    val role = varchar("role", 32)
    val status = varchar("status", 32)
}
