package com.gssg.assets.persistence.domain.topic.pick.repository

import com.gssg.assets.persistence.common.CommonDefinition
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntity
import java.time.LocalDate

/**
 * @Author Heli
 */
interface PickRepository {

    fun insert(definition: PickDefinition)

    fun update(id: Long, definition: PickDefinition)

    fun findById(id: Long): PickEntity?

    fun findByTargetDate(targetDate: LocalDate): List<PickEntity>

    data class PickDefinition(
        val topicId: Long,
        val targetDate: LocalDate
    ) : CommonDefinition
}
