package com.gssg.assets.application.domain.article.service

import com.gssg.assets.application.domain.article.port.`in`.WriteArticleUseCase
import com.gssg.assets.application.domain.article.port.out.MockArticlePersistencePortAdapter
import com.gssg.assets.application.domain.topic.pick.port.out.MockPickPersistencePortAdapter
import com.gssg.assets.domain.article.*
import com.gssg.assets.domain.member.*
import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicDescription
import com.gssg.assets.domain.topic.base.TopicText
import com.gssg.assets.domain.topic.pick.Pick
import com.gssg.assets.domain.topic.pick.PickTargetDate
import com.gssg.assets.domain.topic.pick.PickTopic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate

/**
 * @Author Heli
 */
internal class WriteArticleTest {

    private val pickPersistencePort = MockPickPersistencePortAdapter()

    private val articlePersistencePort = MockArticlePersistencePortAdapter(
        pickPersistencePort = pickPersistencePort
    )

    private val writeArticle = WriteArticle(
        articlePersistencePort = articlePersistencePort
    )

    @BeforeEach
    fun init() {
        articlePersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        articlePersistencePort.clear()
    }

    @Test
    fun `게시글을 작성할 수 있다`() {
        assertDoesNotThrow {
            writeArticle()
        }
        assertThat(true)
    }

    private fun writeArticle() {
        val command = WriteArticleUseCase.Command(
            article = Article.create(
                existAuthor = ArticleAuthor(
                    author = Member.create(
                        newEmail = MemberEmail(email = "heli@example.com"),
                        newPassword = MemberPassword(password = "password"),
                        newDisplayName = MemberDisplayName(displayName = "Heli"),
                        newIntroduce = MemberIntroduce(introduce = "Hello, I am Heli")
                    )
                ),
                existPick = ArticlePick(
                    pick = Pick.create(
                        existTopic = PickTopic(
                            topic = Topic.create(
                                newText = TopicText(text = "First"),
                                newDescription = TopicDescription(description = "First Description")
                            )
                        ),
                        newTargetDate = PickTargetDate(targetDate = LocalDate.now())
                    )
                ),
                newTitle = ArticleTitle(title = "Article Title"),
                newContent = ArticleContent(content = "Article Content")
            )
        )
        writeArticle.command(command = command)
    }
}
