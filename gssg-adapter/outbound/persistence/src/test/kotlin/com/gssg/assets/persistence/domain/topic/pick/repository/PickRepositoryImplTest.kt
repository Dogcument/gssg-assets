package com.gssg.assets.persistence.domain.topic.pick.repository

import com.gssg.assets.config.utils.notNull
import com.gssg.assets.persistence.ExposedRepositoryTestManager
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntities
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntities
import org.assertj.core.api.Assertions
import org.jetbrains.exposed.sql.insert
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class PickRepositoryImplTest : ExposedRepositoryTestManager(
    tables = arrayOf(TopicEntities, PickEntities),
    initStatement = {
        val now = LocalDateTime.now()
        TopicEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[text] = "Temporary Text"
            it[description] = "Temporary Description"
        }
    }
) {

    private val pickRepository = PickRepositoryImpl()

    @Test
    fun `글감 피커를 데이터베이스에 INSERT 할 수 있다`() {
        val nowDate = LocalDate.now()
        val definition = PickRepository.PickDefinition(
            topicId = 1L,
            targetDate = nowDate
        )

        runTestTransaction {
            pickRepository.insert(definition = definition)
            val actual = pickRepository.findById(1L)
            Assertions.assertThat(actual).notNull()
            Assertions.assertThat(actual!!.id.value).isEqualTo(1L)
            Assertions.assertThat(actual.topic.id.value).isEqualTo(1L)
            Assertions.assertThat(actual.targetDate).isEqualTo(nowDate)
        }
    }
}
