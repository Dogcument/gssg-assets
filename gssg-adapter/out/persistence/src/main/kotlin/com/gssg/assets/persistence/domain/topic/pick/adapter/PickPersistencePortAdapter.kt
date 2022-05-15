package com.gssg.assets.persistence.domain.topic.pick.adapter

import com.gssg.assets.application.domain.topic.pick.port.out.PickPersistencePort
import com.gssg.assets.domain.topic.pick.Pick
import com.gssg.assets.domain.topic.pick.PickId
import com.gssg.assets.domain.topic.pick.PickTargetDate
import com.gssg.assets.persistence.domain.topic.pick.adapter.mapper.PickMapper
import com.gssg.assets.persistence.domain.topic.pick.repository.PickRepository

/**
 * @Author Heli
 */
class PickPersistencePortAdapter(
    private val pickRepository: PickRepository
) : PickPersistencePort {
    override fun insert(pick: Pick) {
        val definition = PickMapper.toDefinition(pick)
        pickRepository.insert(definition = definition)
    }

    override fun update(pick: Pick) {
        val definition = PickMapper.toDefinition(pick)
        pickRepository.update(id = pick.longId, definition = definition)
    }

    override fun findById(pickId: PickId): Pick? {
        val pickEntity = pickRepository.findById(id = pickId.id) ?: return null
        return PickMapper.toApplication(pickEntity = pickEntity)
    }

    override fun findByTargetDate(pickTargetDate: PickTargetDate): List<Pick> {
        val pickEntities = pickRepository.findByTargetDate(
            targetDate = pickTargetDate.targetDate
        )
        return pickEntities.map {
            PickMapper.toApplication(pickEntity = it)
        }
    }
}
