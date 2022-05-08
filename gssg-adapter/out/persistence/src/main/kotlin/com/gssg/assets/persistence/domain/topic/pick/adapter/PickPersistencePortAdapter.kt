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
        val pickDefinition = PickMapper.toDefinition(pick)
        pickRepository.insert(pickDefinition = pickDefinition)
    }

    override fun update(pick: Pick) {
        val pickDefinition = PickMapper.toDefinition(pick)
        pickRepository.update(id = pick.longId, pickDefinition = pickDefinition)
    }

    override fun findById(pickId: PickId): Pick? {
        val pickEntity = pickRepository.findById(id = pickId.id) ?: return null
        return PickMapper.toApplication(pickEntity = pickEntity)
    }

    override fun findByTargetDate(pickTargetDate: PickTargetDate): Pick? {
        // FIXME: 2022/05/09
        TODO("하루에 할당되는 글감 2개 이상될 수 있는지 확인")
    }
}
