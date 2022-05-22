package com.gssg.assets.persistence.domain.reaction.article.adapter

import com.gssg.assets.application.domain.reaction.article.port.out.ArticleReactionPersistencePort
import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.reaction.article.ArticleReaction
import com.gssg.assets.persistence.domain.reaction.article.adapter.mapper.ArticleReactionMapper
import com.gssg.assets.persistence.domain.reaction.article.repository.ArticleReactionRepository

/**
 * @Author Heli
 */
class ArticleReactionPersistencePortAdapter(
    private val articleReactionRepository: ArticleReactionRepository
) : ArticleReactionPersistencePort {

    override fun insert(articleReaction: ArticleReaction) {
        val definition = ArticleReactionMapper.toDefinition(articleReaction = articleReaction)
        articleReactionRepository.insert(definition = definition)
    }

    override fun update(articleReaction: ArticleReaction) {
        val definition = ArticleReactionMapper.toDefinition(articleReaction = articleReaction)
        articleReactionRepository.update(id = articleReaction.longId, definition = definition)
    }

    override fun findBy(articleId: ArticleId): List<ArticleReaction> {
        val articleReactionEntities = articleReactionRepository.findByTarget(
            targetId = articleId.id
        )
        return articleReactionEntities.map {
            ArticleReactionMapper.toApplication(articleReactionEntity = it)
        }
    }
}
