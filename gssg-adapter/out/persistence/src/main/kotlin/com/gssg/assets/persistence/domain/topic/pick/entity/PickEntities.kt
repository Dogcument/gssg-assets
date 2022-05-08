package com.gssg.assets.persistence.domain.topic.pick.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable
import org.jetbrains.exposed.sql.javatime.date

/**
 * @Author Heli
 */
object PickEntities : CommonLongIdEntityTable(name = "pick") {
    val topicId = long("topicId")
    val targetDate = date("targetDate")
}
