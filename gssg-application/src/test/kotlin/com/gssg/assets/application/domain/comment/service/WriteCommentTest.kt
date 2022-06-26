package com.gssg.assets.application.domain.comment.service

import com.gssg.assets.application.domain.comment.port.`in`.WriteCommentUseCase
import com.gssg.assets.application.domain.comment.port.out.MockCommentPersistencePortAdapter
import com.gssg.assets.domain.comment.Comment
import com.gssg.assets.domain.comment.CommentArticleId
import com.gssg.assets.domain.comment.CommentAuthorId
import com.gssg.assets.domain.comment.CommentContent
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * @Author Heli
 */
internal class WriteCommentTest {

    private val commentPersistencePort = MockCommentPersistencePortAdapter()

    private val writeComment = WriteComment(
        commentPersistencePort = commentPersistencePort
    )

    @BeforeEach
    fun init() {
        commentPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        commentPersistencePort.clear()
    }

    @Test
    fun `댓글을 작성할 수 있다`() {
        assertDoesNotThrow {
            writeComment()
        }
        Assertions.assertThat(true)
    }

    private fun writeComment() {
        val command = WriteCommentUseCase.Command(
            comment = Comment.create(
                newContent = CommentContent(content = "Hello, I am Heli"),
                authorId = CommentAuthorId(id = 1L),
                articleId = CommentArticleId(id = 1L)
            )
        )
        writeComment.command(command = command)
    }
}
