package com.gssg.assets.persistence.domain.topic.base.adapter.mapper

import com.gssg.assets.domain.logger
import com.gssg.assets.domain.topic.base.*
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntity
import com.gssg.assets.persistence.domain.topic.base.repository.TopicRepository

/**
 * @Author Heli
 */
object TopicMapper {

    private val logger = logger()

    fun toDefinition(topic: Topic): TopicRepository.TopicDefinition {
        logger.info("어댑터 모듈의 매퍼에서 글감 객체를 엔티티 정의로 변경")
        return TopicRepository.TopicDefinition(
            text = topic.text.text,
            description = topic.description.description
        )
    }

    fun toApplication(topicEntity: TopicEntity): Topic {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 글감 객체로 변경")
        return Topic(
            id = TopicId(topicEntity.id.value),
            createdAt = TopicCreatedAt(topicEntity.createdAt),
            modifiedAt = TopicModifiedAt(topicEntity.modifiedAt),
            text = TopicText(topicEntity.text),
            description = TopicDescription(topicEntity.description)
        )
    }
}
