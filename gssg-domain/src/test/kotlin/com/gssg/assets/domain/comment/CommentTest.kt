package com.gssg.assets.domain.comment

import com.gssg.assets.domain.comment.enums.Status
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class CommentTest {

    companion object {
        private const val DEFAULT_COMMENT_CONTENT = "Hi I am Heli"
        private const val DEFAULT_COMMENT_AUTHOR_ID = 1L
        private const val DEFAULT_COMMENT_ARTICLE_ID = 1L
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val actual = generateComment()

        Assertions.assertThat(actual.longId).isEqualTo(-1L)
        Assertions.assertThat(actual.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(actual.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Create 시 값이 의도대로 세팅되어 있다`() {
        val actual = generateComment()

        Assertions.assertThat(actual.content.content).isEqualTo(DEFAULT_COMMENT_CONTENT)
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_COMMENT_AUTHOR_ID)
        Assertions.assertThat(actual.articleId.id).isEqualTo(DEFAULT_COMMENT_ARTICLE_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Update 시 값이 의도대로 세팅되어 있다 - 1`() {
        val exist = generateComment()

        val actual = exist.update(null)

        Assertions.assertThat(actual.content.content).isEqualTo(DEFAULT_COMMENT_CONTENT)
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_COMMENT_AUTHOR_ID)
        Assertions.assertThat(actual.articleId.id).isEqualTo(DEFAULT_COMMENT_ARTICLE_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Update 시 값이 의도대로 세팅되어 있다 - 2`() {
        val exist = generateComment()

        val actual = exist.update(
            newContent = CommentContent("Hi I am Heli 2")
        )

        Assertions.assertThat(actual.content.content).isEqualTo("Hi I am Heli 2")
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_COMMENT_AUTHOR_ID)
        Assertions.assertThat(actual.articleId.id).isEqualTo(DEFAULT_COMMENT_ARTICLE_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Delete 시 다른 값은 그대로며 Status 만 DELETE 로 바뀐다`() {
        val exist = generateComment()

        val actual = exist.delete()

        Assertions.assertThat(actual.content.content).isEqualTo(DEFAULT_COMMENT_CONTENT)
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_COMMENT_AUTHOR_ID)
        Assertions.assertThat(actual.articleId.id).isEqualTo(DEFAULT_COMMENT_ARTICLE_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.DELETED.name)
    }

    private fun generateComment() = Comment.create(
        newContent = CommentContent(DEFAULT_COMMENT_CONTENT),
        authorId = CommentAuthorId(DEFAULT_COMMENT_AUTHOR_ID),
        articleId = CommentArticleId(DEFAULT_COMMENT_ARTICLE_ID),
    )
}
