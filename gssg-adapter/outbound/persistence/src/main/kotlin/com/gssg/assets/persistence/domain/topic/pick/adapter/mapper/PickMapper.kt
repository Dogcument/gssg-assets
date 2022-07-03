package com.gssg.assets.persistence.domain.topic.pick.adapter.mapper

import com.gssg.assets.domain.logger
import com.gssg.assets.domain.topic.pick.*
import com.gssg.assets.persistence.domain.topic.base.adapter.mapper.TopicMapper
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntity
import com.gssg.assets.persistence.domain.topic.pick.repository.PickRepository

/**
 * @Author Heli
 */
object PickMapper {

    private val logger = logger()

    fun toDefinition(pick: Pick): PickRepository.PickDefinition {
        logger.info("어댑터 모듈의 매퍼에서 글감 피커 객체를 엔티티 정의로 변경")
        return PickRepository.PickDefinition(
            topicId = pick.topic.topic.longId,
            targetDate = pick.targetDate.targetDate
        )
    }

    fun toApplication(pickEntity: PickEntity): Pick {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 글감 피커 객체로 변경")
        return Pick(
            id = PickId(pickEntity.id.value),
            createdAt = PickCreatedAt(pickEntity.createdAt),
            modifiedAt = PickModifiedAt(pickEntity.modifiedAt),
            topic = PickTopic(TopicMapper.toApplication(pickEntity.topic)),
            targetDate = PickTargetDate(pickEntity.targetDate)
        )
    }
}
