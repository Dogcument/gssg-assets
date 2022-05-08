package com.gssg.assets.persistence.domain.member.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable

/**
 * @Author Heli
 */
object MemberEntities : CommonLongIdEntityTable(name = "member") {
    val email = varchar("email", 50)
    val password = varchar("password", 128)
    val displayName = varchar("displayName", 20)
    val introduce = varchar("introduce", 32)
    val profileDog = varchar("profileDog", 32)
    val role = varchar("role", 32)
    val status = varchar("status", 32)
}
