package com.gssg.assets.persistence.domain.reaction.article.repository

import com.gssg.assets.config.utils.notNull
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.reaction.enums.Status
import com.gssg.assets.persistence.ExposedRepositoryTestManager
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import com.gssg.assets.persistence.domain.reaction.article.entity.ArticleReactionEntities
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntities
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntities
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.insert
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class ArticleReactionRepositoryImplTest : ExposedRepositoryTestManager(
    tables = arrayOf(
        MemberEntities,
        TopicEntities,
        PickEntities,
        ArticleEntities,
        ArticleReactionEntities
    ),
    initStatement = {
        val now = LocalDateTime.now()
        MemberEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[email] = "heli@example.com"
            it[password] = "password"
            it[displayName] = "Heli"
            it[introduce] = "Hi I am heli"
            it[profileDog] = ProfileDogType.ALEX.name
            it[role] = Role.ADMIN.name
            it[status] = com.gssg.assets.domain.member.enums.Status.ACTIVE.name
        }
        TopicEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[text] = "first topic"
            it[description] = "first topic description"
        }
        PickEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[topicId] = 1L
            it[targetDate] = now.toLocalDate()
        }
        ArticleEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[title] = "Article Title"
            it[content] = "Article Content"
            it[authorId] = 1L
            it[pickId] = 1L
            it[status] = com.gssg.assets.domain.article.enums.Status.ACTIVE.name
        }
    }
) {

    private val articleReactionRepository = ArticleReactionRepositoryImpl()

    @Test
    fun `게시글 리액션을 데이터베이스에 INSERT 할 수 있다`() {
        val definition = ArticleReactionRepository.ArticleReactionDefinition(
            reactorId = 1L,
            targetId = 1L,
            status = Status.ACTIVE
        )

        runTestTransaction {
            articleReactionRepository.insert(definition = definition)
            val actual = articleReactionRepository.findById(1L)
            assertThat(actual).notNull()
            assertThat(actual!!.id.value).isEqualTo(1L)
            assertThat(actual.reactor.id.value).isEqualTo(1L)
            assertThat(actual.target.id.value).isEqualTo(1L)
        }
    }
}
