package com.gssg.assets.persistence.domain.topic.pick.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntities
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntity
import org.jetbrains.exposed.sql.statements.UpdateBuilder

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
        it[topicId] = pickDefinition.topicId
        it[targetDate] = pickDefinition.targetDate
    }

    override fun findById(id: Long): PickEntity? {
        return queryById(id = id)
    }
}
