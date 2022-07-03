package com.gssg.assets.domain.article

import com.gssg.assets.domain.article.enums.Status
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class ArticleTest {

    companion object {
        private const val DEFAULT_ARTICLE_TITLE = "Hello"
        private const val DEFAULT_ARTICLE_CONTENT = "Hi I am Heli"
        private const val DEFAULT_ARTICLE_AUTHOR_ID = 1L
        private const val DEFAULT_ARTICLE_PICK_ID = 1L
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val actual = generateArticle()

        Assertions.assertThat(actual.longId).isEqualTo(-1L)
        Assertions.assertThat(actual.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(actual.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Create 시 값이 의도대로 세팅되어 있다`() {
        val actual = generateArticle()

        Assertions.assertThat(actual.title.title).isEqualTo(DEFAULT_ARTICLE_TITLE)
        Assertions.assertThat(actual.content.content).isEqualTo(DEFAULT_ARTICLE_CONTENT)
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_ARTICLE_AUTHOR_ID)
        Assertions.assertThat(actual.pickId.id).isEqualTo(DEFAULT_ARTICLE_PICK_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Update 시 값이 의도대로 세팅되어 있다 - 1`() {
        val exist = generateArticle()

        val actual = exist.update(null, null)

        Assertions.assertThat(actual.title.title).isEqualTo(DEFAULT_ARTICLE_TITLE)
        Assertions.assertThat(actual.content.content).isEqualTo(DEFAULT_ARTICLE_CONTENT)
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_ARTICLE_AUTHOR_ID)
        Assertions.assertThat(actual.pickId.id).isEqualTo(DEFAULT_ARTICLE_PICK_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Update 시 값이 의도대로 세팅되어 있다 - 2`() {
        val exist = generateArticle()

        val actual = exist.update(
            newTitle = ArticleTitle("Title 2"),
            newContent = ArticleContent("Content 2")
        )

        Assertions.assertThat(actual.title.title).isEqualTo("Title 2")
        Assertions.assertThat(actual.content.content).isEqualTo("Content 2")
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_ARTICLE_AUTHOR_ID)
        Assertions.assertThat(actual.pickId.id).isEqualTo(DEFAULT_ARTICLE_PICK_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Delete 시 다른 값은 그대로이며 Status 만 DELETE 로 바뀐다`() {
        val exist = generateArticle()

        val actual = exist.delete()

        Assertions.assertThat(actual.title.title).isEqualTo(DEFAULT_ARTICLE_TITLE)
        Assertions.assertThat(actual.content.content).isEqualTo(DEFAULT_ARTICLE_CONTENT)
        Assertions.assertThat(actual.authorId.id).isEqualTo(DEFAULT_ARTICLE_AUTHOR_ID)
        Assertions.assertThat(actual.pickId.id).isEqualTo(DEFAULT_ARTICLE_PICK_ID)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.DELETED.name)
    }

    private fun generateArticle() = Article.create(
        newTitle = ArticleTitle(DEFAULT_ARTICLE_TITLE),
        newContent = ArticleContent(DEFAULT_ARTICLE_CONTENT),
        authorId = ArticleAuthorId(DEFAULT_ARTICLE_AUTHOR_ID),
        pickId = ArticlePickId(DEFAULT_ARTICLE_PICK_ID)
    )
}
