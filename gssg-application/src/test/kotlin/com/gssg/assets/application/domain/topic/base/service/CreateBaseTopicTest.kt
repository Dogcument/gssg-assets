package com.gssg.assets.application.domain.topic.base.service

import com.gssg.assets.application.domain.topic.base.port.`in`.CreateBaseTopicUseCase
import com.gssg.assets.application.domain.topic.base.port.out.MockTopicPersistencePortAdapter
import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicDescription
import com.gssg.assets.domain.topic.base.TopicText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * @Author Heli
 */
internal class CreateBaseTopicTest {

    private val topicPersistencePort = MockTopicPersistencePortAdapter()

    private val createBaseTopic = CreateBaseTopic(
        topicPersistencePort = topicPersistencePort
    )

    @BeforeEach
    fun init() {
        topicPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        topicPersistencePort.clear()
    }

    @Test
    fun `글감을 생성할 수 있다`() {
        assertDoesNotThrow {
            createTopic()
        }
        assertThat(true)
    }

    private fun createTopic() {
        val command = CreateBaseTopicUseCase.Command(
            topic = Topic.create(
                newText = TopicText("New Topic Text"),
                newDescription = TopicDescription("New Topic Description")
            )
        )
        createBaseTopic.command(command = command)
    }
}
