package com.gssg.assets.persistence.domain.reaction.article.repository

import com.gssg.assets.domain.reaction.enums.Status
import com.gssg.assets.persistence.domain.reaction.article.entity.ArticleReactionEntity

/**
 * @Author Heli
 */
interface ArticleReactionRepository {

    fun insert(definition: ArticleReactionDefinition)

    fun update(id: Long, definition: ArticleReactionDefinition)

    fun findById(id: Long): ArticleReactionEntity?

    fun findByTarget(targetId: Long): List<ArticleReactionEntity>

    data class ArticleReactionDefinition(
        val reactorId: Long,
        val targetId: Long,
        val status: Status
    )
}
