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
    CommonRepository<Long, PickEntity, PickEntities>(PickEntities) {

    override fun insert(pickDefinition: PickRepository.PickDefinition) {
        execInsert {
            insertOrUpdate(it, pickDefinition)
        }
    }

    override fun update(id: Long, pickDefinition: PickRepository.PickDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, pickDefinition)
        }
    }

    private fun PickEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        pickDefinition: PickRepository.PickDefinition
    ) {
        it[topicId] = pickDefinition.topicIds
        it[targetDate] = pickDefinition.targetDate
    }

    override fun findById(id: Long): PickEntity? {
        return queryById(id = id)
    }

    override fun findByTargetDate(targetDate: LocalDate): List<PickEntity> {
        return PickEntity.find { PickEntities.targetDate eq targetDate }.toList()
    }
}
