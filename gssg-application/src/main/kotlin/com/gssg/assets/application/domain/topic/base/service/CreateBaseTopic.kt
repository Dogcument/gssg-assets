package com.gssg.assets.application.domain.topic.base.service

import com.gssg.assets.application.domain.topic.base.port.`in`.CreateBaseTopicUseCase
import com.gssg.assets.application.domain.topic.base.port.out.TopicPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class CreateBaseTopic(
    private val topicPersistencePort: TopicPersistencePort
) : CreateBaseTopicUseCase {

    private val logger = logger()

    override fun command(command: CreateBaseTopicUseCase.Command) {
        val topic = command.topic

        logger.info("베이스 토픽을 데이터베이스에 저장하기 위해 영속성 포트 호출 [Topic.Text=${topic.text.text}]")
        topicPersistencePort.insert(topic)
    }
}
