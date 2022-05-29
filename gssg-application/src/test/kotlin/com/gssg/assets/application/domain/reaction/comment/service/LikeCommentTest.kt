package com.gssg.assets.application.domain.reaction.comment.service

import com.gssg.assets.application.domain.reaction.comment.port.`in`.LikeCommentUseCase
import com.gssg.assets.application.domain.reaction.comment.port.out.MockCommentReactionPersistencePortAdapter
import com.gssg.assets.domain.reaction.comment.CommentReactionReactorId
import com.gssg.assets.domain.reaction.comment.CommentReactionTargetId
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * @Author Heli
 */
internal class LikeCommentTest {

    private val commentReactionPersistencePort = MockCommentReactionPersistencePortAdapter()

    private val likeComment = LikeComment(
        commentReactionPersistencePort = commentReactionPersistencePort
    )

    @BeforeEach
    fun init() {
        commentReactionPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        commentReactionPersistencePort.clear()
    }

    @Test
    fun `댓글에 좋아요 할 수 있다`() {
        assertDoesNotThrow {
            likeComment()
        }
        Assertions.assertThat(true)
    }

    private fun likeComment() {
        val command = LikeCommentUseCase.Command(
            reactorId = CommentReactionReactorId(reactorId = 1L),
            targetId = CommentReactionTargetId(targetId = 1L)
        )
        likeComment.command(command = command)
    }

}
