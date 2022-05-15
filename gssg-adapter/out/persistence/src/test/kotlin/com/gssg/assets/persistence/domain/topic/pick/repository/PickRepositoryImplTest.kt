package com.gssg.assets.persistence.domain.topic.pick.repository

import com.gssg.assets.config.utils.notNull
import com.gssg.assets.persistence.MockTransactionRunManager
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntities
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntities
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntity
import org.assertj.core.api.Assertions
import org.jetbrains.exposed.sql.insert
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class PickRepositoryImplTest : MockTransactionRunManager(
    tables = arrayOf(TopicEntities, PickEntities),
    initStatement = {
        TopicEntities.insert {
            it[createdAt] = LocalDateTime.now()
            it[modifiedAt] = LocalDateTime.now()
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

        runTransaction {
            pickRepository.insert(definition)
            val actual = PickEntity.findById(1L)
            Assertions.assertThat(actual).notNull()
            Assertions.assertThat(actual?.id?.value).isEqualTo(1L)
            Assertions.assertThat(actual?.topicId?.value).isEqualTo(1L)
            Assertions.assertThat(actual?.targetDate).isEqualTo(nowDate)
        }
    }
}
