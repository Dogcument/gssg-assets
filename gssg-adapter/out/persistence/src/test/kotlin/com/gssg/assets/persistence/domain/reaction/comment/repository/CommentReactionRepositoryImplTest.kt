package com.gssg.assets.persistence.domain.reaction.comment.repository

import com.gssg.assets.config.utils.notNull
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.reaction.enums.ReactionType
import com.gssg.assets.persistence.ExposedRepositoryTestManager
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.comment.entity.CommentEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import com.gssg.assets.persistence.domain.reaction.comment.entity.CommentReactionEntities
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntities
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntities
import org.assertj.core.api.Assertions
import org.jetbrains.exposed.sql.insert
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class CommentReactionRepositoryImplTest : ExposedRepositoryTestManager(
    tables = arrayOf(
        MemberEntities,
        TopicEntities,
        PickEntities,
        ArticleEntities,
        CommentEntities,
        CommentReactionEntities
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
        CommentEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[content] = "Comment Content"
            it[authorId] = 1L
            it[articleId] = 1L
            it[status] = com.gssg.assets.domain.comment.enums.Status.ACTIVE.name
        }
    }
) {

    private val commentReactionRepository = CommentReactionRepositoryImpl()

    @Test
    fun `?????? ???????????? ????????????????????? INSERT ??? ??? ??????`() {
        val definition = CommentReactionRepository.CommentReactionDefinition(
            reactorId = 1L,
            targetId = 1L,
            type = ReactionType.LIKE
        )

        runTestTransaction {
            commentReactionRepository.insert(definition = definition)
            val actual = commentReactionRepository.findById(1L)
            Assertions.assertThat(actual).notNull()
            Assertions.assertThat(actual!!.id.value).isEqualTo(1L)
            Assertions.assertThat(actual.type).isEqualTo(ReactionType.LIKE.name)
            Assertions.assertThat(actual.reactor.id.value).isEqualTo(1L)
            Assertions.assertThat(actual.target.id.value).isEqualTo(1L)
        }
    }
}
