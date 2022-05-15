package com.gssg.assets.application.domain.topic.base.service

import com.gssg.assets.application.domain.topic.base.port.`in`.QueryBaseTopicUseCase
import com.gssg.assets.application.domain.topic.base.port.out.MockTopicPersistencePortAdapter
import com.gssg.assets.domain.topic.base.TopicId
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
internal class QueryBaseTopicTest {

    private val topicPersistencePort = MockTopicPersistencePortAdapter()

    private val queryBaseTopic = QueryBaseTopic(
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
    fun `Id 로 글감을 찾을 수 있다`() {
        val query = QueryBaseTopicUseCase.Query(
            topicId = TopicId(id = 1L)
        )
        val result = queryBaseTopic.queryById(query = query)
        Assertions.assertNotNull(result)
    }

    @Test
    fun `등록되지 않은 Id 로 검색 시 null 을 반환한다`() {
        val query = QueryBaseTopicUseCase.Query(
            topicId = TopicId(id = 4L)
        )
        val result = queryBaseTopic.queryById(query = query)
        Assertions.assertNull(result)
    }
}
