package com.gssg.assets.domain.topic.pick

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class PickTest {

    companion object {
        private const val DEFAULT_PICK_TOPIC_ID = 1L
        private val DEFAULT_PICK_TARGET_DATE = LocalDate.now()
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val actual = generatePick()

        Assertions.assertThat(actual.longId).isEqualTo(-1L)
        Assertions.assertThat(actual.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(actual.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Create 시 값이 의도대로 설정되어 있다`() {

        val actual = generatePick()

        Assertions.assertThat(actual.topicId.topicId).isEqualTo(DEFAULT_PICK_TOPIC_ID)
        Assertions.assertThat(actual.targetDate.targetDate).isEqualTo(DEFAULT_PICK_TARGET_DATE)
    }

    private fun generatePick() = Pick.create(
        existTopicId = PickTopicId(DEFAULT_PICK_TOPIC_ID),
        newTargetDate = PickTargetDate(DEFAULT_PICK_TARGET_DATE)
    )
}
