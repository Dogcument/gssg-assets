package com.gssg.assets.application.domain.reaction.article.service

import com.gssg.assets.application.domain.reaction.article.port.`in`.LikeArticleUseCase
import com.gssg.assets.application.domain.reaction.article.port.out.MockArticleReactionPersistencePortAdapter
import com.gssg.assets.domain.reaction.article.ArticleReactionReactorId
import com.gssg.assets.domain.reaction.article.ArticleReactionTargetId
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * @Author Heli
 */
internal class LikeArticleTest {

    private val articleReactionPersistencePort = MockArticleReactionPersistencePortAdapter()

    private val likeArticle = LikeArticle(
        articleReactionPersistencePort = articleReactionPersistencePort
    )

    @BeforeEach
    fun init() {
        articleReactionPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        articleReactionPersistencePort.clear()
    }

    @Test
    fun `게시글에 좋아요 할 수 있다`() {
        assertDoesNotThrow {
            likeArticle()
        }
        Assertions.assertThat(true)
    }

    private fun likeArticle() {
        val command = LikeArticleUseCase.Command(
            reactorId = ArticleReactionReactorId(reactorId = 1L),
            targetId = ArticleReactionTargetId(targetId = 1L)
        )
        likeArticle.command(command = command)
    }
}
