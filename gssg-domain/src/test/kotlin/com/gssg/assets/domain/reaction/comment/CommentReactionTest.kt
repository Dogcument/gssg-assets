package com.gssg.assets.domain.reaction.comment

import com.gssg.assets.domain.reaction.enums.ReactionType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class CommentReactionTest {
    companion object {
        private const val DEFAULT_COMMENT_REACTION_REACTOR_ID = 1L
        private const val DEFAULT_COMMENT_REACTION_TARGET_ID = 2L
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val actual = likeCommentReaction()

        Assertions.assertThat(actual.longId).isEqualTo(-1L)
        Assertions.assertThat(actual.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(actual.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Like 시 값이 의도대로 세팅되어 있다`() {
        val actual = likeCommentReaction()

        Assertions.assertThat(actual.reactorId.reactorId).isEqualTo(DEFAULT_COMMENT_REACTION_REACTOR_ID)
        Assertions.assertThat(actual.targetId.targetId).isEqualTo(DEFAULT_COMMENT_REACTION_TARGET_ID)
        Assertions.assertThat(actual.type.type.name).isEqualTo(ReactionType.LIKE.name)
    }

    @Test
    fun `Cancel 시 다른 값은 그대로이며 type 만 NONE 으로 바뀐다`() {
        val exist = likeCommentReaction()

        val actual = exist.cancel()

        Assertions.assertThat(actual.reactorId.reactorId).isEqualTo(DEFAULT_COMMENT_REACTION_REACTOR_ID)
        Assertions.assertThat(actual.targetId.targetId).isEqualTo(DEFAULT_COMMENT_REACTION_TARGET_ID)
        Assertions.assertThat(actual.type.type.name).isEqualTo(ReactionType.NONE.name)
    }

    private fun likeCommentReaction() = CommentReaction.like(
        reactorId = CommentReactionReactorId(DEFAULT_COMMENT_REACTION_REACTOR_ID),
        targetId = CommentReactionTargetId(DEFAULT_COMMENT_REACTION_TARGET_ID)
    )
}
