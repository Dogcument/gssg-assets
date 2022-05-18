package com.gssg.assets.domain.article

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.article.enums.Status
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Article(
    override val id: ArticleId = ArticleId(-1L),
    val createdAt: ArticleCreatedAt = ArticleCreatedAt(LocalDateTime.MIN),
    val modifiedAt: ArticleModifiedAt = ArticleModifiedAt(LocalDateTime.MIN),
    val title: ArticleTitle,
    val content: ArticleContent,
    val author: ArticleAuthor,
    val status: ArticleStatus
) : BaseDomain() {

    companion object {
        fun create(
            newTitle: ArticleTitle,
            newContent: ArticleContent,
            author: ArticleAuthor
        ) = Article(
            title = newTitle,
            content = newContent,
            author = author,
            status = ArticleStatus(status = Status.ACTIVE)
        )
    }

    fun update(
        newTitle: ArticleTitle?,
        newContent: ArticleContent?
    ): Article {
        val title = newTitle ?: title
        val content = newContent ?: content
        return Article(
            id = id,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            title = title,
            content = content,
            author = author,
            status = status
        )
    }

    fun delete() = Article(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        title = title,
        content = content,
        author = author,
        status = ArticleStatus(status = Status.DELETED)
    )
}
