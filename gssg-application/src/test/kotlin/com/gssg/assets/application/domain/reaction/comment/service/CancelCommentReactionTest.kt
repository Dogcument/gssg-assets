package com.gssg.assets.application.domain.reaction.comment.service

import com.gssg.assets.application.domain.reaction.comment.port.`in`.CancelCommentReactionUseCase
import com.gssg.assets.application.domain.reaction.comment.port.out.MockCommentReactionPersistencePortAdapter
import com.gssg.assets.application.domain.reaction.comment.service.exception.CommentReactionNotFoundException
import com.gssg.assets.domain.reaction.comment.CommentReactionId
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*

/**
 * @Author Heli
 */
internal class CancelCommentReactionTest {

    private val commentReactionPersistencePort = MockCommentReactionPersistencePortAdapter()

    private val cancelCommentReaction = CancelCommentReaction(
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
    fun `댓글 리액션을 취소 할 수 있다`() {
        assertDoesNotThrow {
            cancelCommentReaction()
        }
        Assertions.assertThat(true)
    }

    @Test
    fun `존재하지 않은 댓글 리액션을 취소하려 하면 Exception 을 발생시킨다`() {
        assertThrows<CommentReactionNotFoundException> {
            cancelCommentReaction(999L)
        }
    }

    private fun cancelCommentReaction(
        commentReactionId: Long = 1L
    ) {
        val command = CancelCommentReactionUseCase.Command(
            commentReactionId = CommentReactionId(id = commentReactionId)
        )
        cancelCommentReaction.command(command = command)
    }
}
