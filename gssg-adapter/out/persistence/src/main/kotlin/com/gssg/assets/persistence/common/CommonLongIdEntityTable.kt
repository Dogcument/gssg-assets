package com.gssg.assets.persistence.common

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

/**
 * @Author Heli
 */
abstract class CommonLongIdEntityTable(name: String = "") : LongIdTable(name = name) {
    val createdAt = datetime("createdAt")
    val modifiedAt = datetime("modifiedAt")
}
