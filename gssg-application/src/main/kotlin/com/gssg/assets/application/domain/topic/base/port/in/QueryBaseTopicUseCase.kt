package com.gssg.assets.application.domain.topic.base.port.`in`

import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicId

/**
 * @Author Heli
 */
interface QueryBaseTopicUseCase {

    fun queryById(query: Query): Result?

    data class Query(
        val topicId: TopicId
    )

    data class Result(
        val topic: Topic
    )
}
