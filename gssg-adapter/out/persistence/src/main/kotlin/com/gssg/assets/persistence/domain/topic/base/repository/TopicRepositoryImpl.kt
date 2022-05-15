package com.gssg.assets.persistence.domain.topic.base.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntities
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntity
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * @Author Heli
 */
class TopicRepositoryImpl : TopicRepository,
    CommonRepository<Long, TopicEntities>(TopicEntities) {

    override fun insert(topicDefinition: TopicRepository.TopicDefinition) {
        execInsert {
            insertOrUpdate(it, topicDefinition)
        }
    }

    override fun update(id: Long, topicDefinition: TopicRepository.TopicDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, topicDefinition)
        }
    }

    private fun TopicEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        topicDefinition: TopicRepository.TopicDefinition
    ) {
        it[text] = topicDefinition.text
        it[description] = topicDefinition.description
    }

    override fun findById(id: Long): TopicEntity? {
        return queryById(id = id) {
            TopicEntity.wrapRow(it)
        }
    }
}
