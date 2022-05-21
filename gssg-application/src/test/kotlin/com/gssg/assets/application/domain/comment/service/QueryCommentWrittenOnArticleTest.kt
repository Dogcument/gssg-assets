package com.gssg.assets.application.domain.comment.service

import com.gssg.assets.application.domain.comment.port.`in`.QueryCommentWrittenOnArticleUseCase
import com.gssg.assets.application.domain.comment.port.out.MockCommentPersistencePortAdapter
import com.gssg.assets.domain.article.ArticleId
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
internal class QueryCommentWrittenOnArticleTest {

    private val commentPersistencePort = MockCommentPersistencePortAdapter()

    private val queryCommentWrittenOnArticle = QueryCommentWrittenOnArticle(
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
    fun `게시글에 작성된 댓글을 가져올 수 있다`() {
        val query = QueryCommentWrittenOnArticleUseCase.Query(
            articleId = ArticleId(id = 1L)
        )
        val result = queryCommentWrittenOnArticle.query(
            query = query
        )
        Assertions.assertThat(result.comments).hasSize(3)
        Assertions.assertThat(result.comments[0].id.id).isEqualTo(1L)
        Assertions.assertThat(result.comments[1].id.id).isEqualTo(2L)
        Assertions.assertThat(result.comments[2].id.id).isEqualTo(3L)

        Assertions.assertThat(result.comments[0].article.article.id.id).isEqualTo(1L)
        Assertions.assertThat(result.comments[1].article.article.id.id).isEqualTo(1L)
        Assertions.assertThat(result.comments[2].article.article.id.id).isEqualTo(1L)

        Assertions.assertThat(result.comments[0].author.author.id.id).isEqualTo(1L)
        Assertions.assertThat(result.comments[1].author.author.id.id).isEqualTo(1L)
        Assertions.assertThat(result.comments[2].author.author.id.id).isEqualTo(1L)
    }
}
