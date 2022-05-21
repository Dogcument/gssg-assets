package com.gssg.assets.persistence.domain.article.adapter

import com.gssg.assets.application.domain.article.port.out.ArticlePersistencePort
import com.gssg.assets.domain.article.Article
import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.topic.pick.PickTargetDate
import com.gssg.assets.persistence.domain.article.adapter.mapper.ArticleMapper
import com.gssg.assets.persistence.domain.article.repository.ArticleRepository

/**
 * @Author Heli
 */
class ArticlePersistencePortAdapter(
    private val articleRepository: ArticleRepository
) : ArticlePersistencePort {

    override fun insert(article: Article) {
        val definition = ArticleMapper.toDefinition(article = article)
        articleRepository.insert(definition = definition)
    }

    override fun update(article: Article) {
        val definition = ArticleMapper.toDefinition(article = article)
        articleRepository.update(id = article.longId, definition = definition)
    }

    override fun findById(articleId: ArticleId): Article? {
        val articleEntity = articleRepository.findById(id = articleId.id) ?: return null
        return ArticleMapper.toApplication(articleEntity = articleEntity)
    }

    override fun findByPickTargetDate(pickTargetDate: PickTargetDate): List<Article> {
        val articleEntities = articleRepository.findByPickTargetDate(
            pickTargetDate = pickTargetDate.targetDate
        )
        return articleEntities.map {
            ArticleMapper.toApplication(articleEntity = it)
        }
    }
}
