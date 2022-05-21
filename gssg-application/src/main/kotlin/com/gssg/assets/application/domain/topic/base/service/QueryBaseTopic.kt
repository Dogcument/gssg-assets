package com.gssg.assets.application.domain.topic.base.service

import com.gssg.assets.application.domain.topic.base.port.`in`.QueryBaseTopicUseCase
import com.gssg.assets.application.domain.topic.base.port.out.TopicPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class QueryBaseTopic(
    private val topicPersistencePort: TopicPersistencePort
) : QueryBaseTopicUseCase {

    private val logger = logger()

    override fun query(query: QueryBaseTopicUseCase.Query): QueryBaseTopicUseCase.Result? {
        val topicId = query.topicId

        logger.info("베이스 토픽을 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        val topic = topicPersistencePort.findById(topicId) ?: return null

        return QueryBaseTopicUseCase.Result(
            topic = topic
        )
    }
}
