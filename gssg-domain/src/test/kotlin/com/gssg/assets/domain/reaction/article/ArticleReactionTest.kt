package com.gssg.assets.domain.reaction.article

import com.gssg.assets.domain.reaction.enums.ReactionType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class ArticleReactionTest {

    companion object {
        private const val DEFAULT_ARTICLE_REACTION_REACTOR_ID = 1L
        private const val DEFAULT_ARTICLE_REACTION_TARGET_ID = 2L
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val actual = likeArticleReaction()

        Assertions.assertThat(actual.longId).isEqualTo(-1L)
        Assertions.assertThat(actual.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(actual.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Like 시 값이 의도대로 세팅되어 있다`() {
        val actual = likeArticleReaction()

        Assertions.assertThat(actual.reactorId.reactorId).isEqualTo(DEFAULT_ARTICLE_REACTION_REACTOR_ID)
        Assertions.assertThat(actual.targetId.targetId).isEqualTo(DEFAULT_ARTICLE_REACTION_TARGET_ID)
        Assertions.assertThat(actual.type.type.name).isEqualTo(ReactionType.LIKE.name)
    }

    @Test
    fun `Cancel 시 다른 값은 그대로이며 type 만 NONE 으로 바뀐다`() {
        val exist = likeArticleReaction()

        val actual = exist.cancel()

        Assertions.assertThat(actual.reactorId.reactorId).isEqualTo(DEFAULT_ARTICLE_REACTION_REACTOR_ID)
        Assertions.assertThat(actual.targetId.targetId).isEqualTo(DEFAULT_ARTICLE_REACTION_TARGET_ID)
        Assertions.assertThat(actual.type.type.name).isEqualTo(ReactionType.NONE.name)
    }

    private fun likeArticleReaction() = ArticleReaction.like(
        reactorId = ArticleReactionReactorId(DEFAULT_ARTICLE_REACTION_REACTOR_ID),
        targetId = ArticleReactionTargetId(DEFAULT_ARTICLE_REACTION_TARGET_ID)
    )
}
