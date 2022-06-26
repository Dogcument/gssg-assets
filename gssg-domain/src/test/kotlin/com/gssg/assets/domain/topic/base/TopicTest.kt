package com.gssg.assets.domain.topic.base

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class TopicTest {

    companion object {
        private const val DEFAULT_TOPIC_TEXT = "Topic Text"
        private const val DEFAULT_TOPIC_DESCRIPTION = "Topic Description"
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val actual = generateTopic()

        Assertions.assertThat(actual.longId).isEqualTo(-1L)
        Assertions.assertThat(actual.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(actual.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Create 시 값이 의도대로 설정되어 있다`() {

        val actual = generateTopic()

        Assertions.assertThat(actual.text.text).isEqualTo(DEFAULT_TOPIC_TEXT)
        Assertions.assertThat(actual.description.description).isEqualTo(DEFAULT_TOPIC_DESCRIPTION)
    }

    private fun generateTopic() = Topic.create(
        newText = TopicText(DEFAULT_TOPIC_TEXT),
        newDescription = TopicDescription(DEFAULT_TOPIC_DESCRIPTION)
    )
}
