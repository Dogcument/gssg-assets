package com.gssg.assets.application.domain.reaction.article.port.out

import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.reaction.article.*
import com.gssg.assets.domain.reaction.enums.ReactionType
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
class MockArticleReactionPersistencePortAdapter : ArticleReactionPersistencePort {

    private val db = mutableMapOf<ArticleReactionId, ArticleReaction>()
    private val distributedId = AtomicLong(1L)

    override fun insert(articleReaction: ArticleReaction) {
        val articleReactionId = ArticleReactionId(id = distributedId.getAndIncrement())
        db[articleReactionId] = articleReaction
    }

    override fun update(articleReaction: ArticleReaction) {
        val articleReactionId = ArticleReactionId(id = articleReaction.longId)
        db[articleReactionId] = articleReaction
    }

    override fun findBy(articleReactionId: ArticleReactionId): ArticleReaction? {
        return db[articleReactionId]
    }

    override fun findBy(articleId: ArticleId): List<ArticleReaction> {
        return db.values.filter {
            it.targetId.targetId == articleId.id
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        db[ArticleReactionId(id = 1L)] = ArticleReaction(
            id = ArticleReactionId(id = 1L),
            createdAt = ArticleReactionCreatedAt(createdAt = now),
            modifiedAt = ArticleReactionModifiedAt(modifiedAt = now),
            reactorId = ArticleReactionReactorId(reactorId = 1L),
            targetId = ArticleReactionTargetId(targetId = 1L),
            type = ArticleReactionType(type = ReactionType.LIKE)
        )
        db[ArticleReactionId(id = 2L)] = ArticleReaction(
            id = ArticleReactionId(id = 2L),
            createdAt = ArticleReactionCreatedAt(createdAt = now),
            modifiedAt = ArticleReactionModifiedAt(modifiedAt = now),
            reactorId = ArticleReactionReactorId(reactorId = 1L),
            targetId = ArticleReactionTargetId(targetId = 1L),
            type = ArticleReactionType(type = ReactionType.LIKE)
        )
        db[ArticleReactionId(id = 3L)] = ArticleReaction(
            id = ArticleReactionId(id = 3L),
            createdAt = ArticleReactionCreatedAt(createdAt = now),
            modifiedAt = ArticleReactionModifiedAt(modifiedAt = now),
            reactorId = ArticleReactionReactorId(reactorId = 1L),
            targetId = ArticleReactionTargetId(targetId = 2L),
            type = ArticleReactionType(type = ReactionType.LIKE)
        )
    }

    fun clear() {
        db.clear()
    }
}
