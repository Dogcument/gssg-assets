package com.gssg.assets.application.domain.reaction.article.service

import com.gssg.assets.application.domain.reaction.article.port.`in`.CancelArticleReactionUseCase
import com.gssg.assets.application.domain.reaction.article.port.out.MockArticleReactionPersistencePortAdapter
import com.gssg.assets.application.domain.reaction.article.service.exception.ArticleReactionNotFoundException
import com.gssg.assets.domain.reaction.article.ArticleReactionId
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*

/**
 * @Author Heli
 */
internal class CancelArticleReactionTest {

    private val articleReactionPersistencePort = MockArticleReactionPersistencePortAdapter()

    private val cancelArticleReaction = CancelArticleReaction(
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
    fun `게시글 리액션을 취소 할 수 있다`() {
        assertDoesNotThrow {
            cancelArticleReaction()
        }
        Assertions.assertThat(true)
    }

    @Test
    fun `존재하지 않은 게시글 리액션을 취소하려 하면 Exception 을 발생시킨다`() {
        assertThrows<ArticleReactionNotFoundException> {
            cancelArticleReaction(999L)
        }
    }

    private fun cancelArticleReaction(
        articleReactionId: Long = 1L
    ) {
        val command = CancelArticleReactionUseCase.Command(
            articleReactionId = ArticleReactionId(id = articleReactionId)
        )
        cancelArticleReaction.command(command = command)
    }
}
