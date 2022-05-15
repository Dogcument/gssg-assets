package com.gssg.assets.application.domain.topic.pick.service

import com.gssg.assets.application.domain.topic.pick.port.`in`.QueryTodayTopicUseCase
import com.gssg.assets.application.domain.topic.pick.port.out.MockPickPersistencePortAdapter
import com.gssg.assets.domain.topic.pick.PickId
import com.gssg.assets.domain.topic.pick.PickTargetDate
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * @Author Heli
 */
internal class QueryTodayTopicTest {

    private val pickPersistencePort = MockPickPersistencePortAdapter()

    private val queryTodayTopic = QueryTodayTopic(
        pickPersistencePort = pickPersistencePort
    )

    @BeforeEach
    fun init() {
        pickPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        pickPersistencePort.clear()
    }

    @Test
    fun `오늘의 글감을 가져올 수 있다`() {
        val query = QueryTodayTopicUseCase.Query(
            targetDate = PickTargetDate(LocalDate.now())
        )
        val result = queryTodayTopic.query(
            query = query
        )
        Assertions.assertThat(result.pickTopics).hasSize(1)
        Assertions.assertThat(result.pickTopics.first().id).isEqualTo(PickId(1L))
        Assertions.assertThat(result.pickTopics.first().targetDate).isEqualTo(
            PickTargetDate(LocalDate.now())
        )
    }
}
