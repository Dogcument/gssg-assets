package com.gssg.assets.persistence.domain.topic.pick.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntities
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntity
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import java.time.LocalDate

/**
 * @Author Heli
 */
class PickRepositoryImpl : PickRepository,
    CommonRepository<Long, PickEntities>(PickEntities) {

    override fun insert(definition: PickRepository.PickDefinition) {
        execInsert {
            insertOrUpdate(it, definition)
        }
    }

    override fun update(id: Long, definition: PickRepository.PickDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, definition)
        }
    }

    private fun PickEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        definition: PickRepository.PickDefinition
    ) {
        it[topicId] = definition.topicId
        it[targetDate] = definition.targetDate
    }

    override fun findById(id: Long): PickEntity? {
        return queryById(id = id) {
            PickEntity.wrapRow(it)
        }
    }

    override fun findByTargetDate(targetDate: LocalDate): List<PickEntity> {
        return PickEntity.find { PickEntities.targetDate eq targetDate }.toList()
    }
}
