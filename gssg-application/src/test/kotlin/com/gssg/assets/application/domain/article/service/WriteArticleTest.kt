package com.gssg.assets.application.domain.article.service

import com.gssg.assets.application.domain.article.port.`in`.WriteArticleUseCase
import com.gssg.assets.application.domain.article.port.out.MockArticlePersistencePortAdapter
import com.gssg.assets.application.domain.topic.pick.port.out.MockPickPersistencePortAdapter
import com.gssg.assets.domain.article.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

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
                authorId = ArticleAuthorId(id = 1L),
                pickId = ArticlePickId(id = 1L),
                newTitle = ArticleTitle(title = "Article Title"),
                newContent = ArticleContent(content = "Article Content")
            )
        )
        writeArticle.command(command = command)
    }
}
