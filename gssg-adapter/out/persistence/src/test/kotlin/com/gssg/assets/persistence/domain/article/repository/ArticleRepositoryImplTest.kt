package com.gssg.assets.persistence.domain.article.repository

import com.gssg.assets.config.utils.notNull
import com.gssg.assets.domain.article.enums.Status
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.persistence.ExposedRepositoryTestManager
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.insert
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class ArticleRepositoryImplTest : ExposedRepositoryTestManager(
    tables = arrayOf(MemberEntities, ArticleEntities),
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
    }
) {

    private val articleRepository = ArticleRepositoryImpl()

    @Test
    fun `게시글을 데이터베이스에 INSERT 할 수 있다`() {
        val definition = ArticleRepository.ArticleDefinition(
            title = "Article Title",
            content = "Article Content",
            authorId = 1L,
            status = Status.ACTIVE
        )

        runTestTransaction {
            articleRepository.insert(definition = definition)
            val actual = articleRepository.findById(1L)
            assertThat(actual).notNull()
            assertThat(actual?.id?.value).isEqualTo(1L)
            assertThat(actual?.title).isEqualTo("Article Title")
            assertThat(actual?.content).isEqualTo("Article Content")
            assertThat(actual?.author?.id?.value).isEqualTo(1L)
            assertThat(actual?.status).isEqualTo(Status.ACTIVE.name)
        }
    }
}
