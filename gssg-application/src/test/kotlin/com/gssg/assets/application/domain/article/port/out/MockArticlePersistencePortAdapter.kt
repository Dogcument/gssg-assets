package com.gssg.assets.application.domain.article.port.out

import com.gssg.assets.application.domain.topic.pick.port.out.PickPersistencePort
import com.gssg.assets.domain.article.*
import com.gssg.assets.domain.article.enums.Status
import com.gssg.assets.domain.topic.pick.PickTargetDate
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
class MockArticlePersistencePortAdapter(
    private val pickPersistencePort: PickPersistencePort
) : ArticlePersistencePort {

    private val db = mutableMapOf<ArticleId, Article>()
    private val distributedId = AtomicLong(1L)

    override fun insert(article: Article) {
        val articleId = ArticleId(id = distributedId.getAndIncrement())
        db[articleId] = article
    }

    override fun update(article: Article) {
        val articleId = ArticleId(id = article.longId)
        db[articleId] = article
    }

    override fun findById(articleId: ArticleId): Article? {
        return db[articleId]
    }

    override fun findByPickTargetDate(pickTargetDate: PickTargetDate): List<Article> {
        val picks = pickPersistencePort.findByTargetDate(
            pickTargetDate = pickTargetDate
        )
        return db.values.filter { article ->
            picks.associateBy { pick ->
                pick.longId
            }.containsKey(article.longId)
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        (1L..3L).forEach { longId ->
            db[ArticleId(id = longId)] = Article(
                id = ArticleId(id = longId),
                createdAt = ArticleCreatedAt(createdAt = now),
                modifiedAt = ArticleModifiedAt(modifiedAt = now),
                title = ArticleTitle(title = "Article Title"),
                content = ArticleContent(content = "Article Content"),
                authorId = ArticleAuthorId(id = 1L),
                pickId = ArticlePickId(id = 1L),
                status = ArticleStatus(status = Status.ACTIVE)
            )
        }
    }

    fun clear() {
        db.clear()
    }
}
