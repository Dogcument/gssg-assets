package com.gssg.assets.persistence.domain.article.adapter.mapper

import com.gssg.assets.domain.article.*
import com.gssg.assets.domain.article.enums.Status
import com.gssg.assets.domain.logger
import com.gssg.assets.persistence.domain.article.entity.ArticleEntity
import com.gssg.assets.persistence.domain.article.repository.ArticleRepository

/**
 * @Author Heli
 */
object ArticleMapper {

    private val logger = logger()

    fun toDefinition(article: Article): ArticleRepository.ArticleDefinition {
        logger.info("어댑터 모듈의 매퍼에서 아티클 객체를 엔티티 정의로 변경")
        return ArticleRepository.ArticleDefinition(
            title = article.title.title,
            content = article.content.content,
            authorId = article.authorId.id,
            pickId = article.pickId.id,
            status = article.status.status
        )
    }

    fun toApplication(articleEntity: ArticleEntity): Article {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 아티클 객체로 변경")
        return Article(
            id = ArticleId(id = articleEntity.id.value),
            createdAt = ArticleCreatedAt(createdAt = articleEntity.createdAt),
            modifiedAt = ArticleModifiedAt(modifiedAt = articleEntity.modifiedAt),
            title = ArticleTitle(title = articleEntity.title),
            content = ArticleContent(content = articleEntity.content),
            authorId = ArticleAuthorId(id = articleEntity.author.id.value),
            pickId = ArticlePickId(id = articleEntity.pick.id.value),
            status = ArticleStatus(status = Status.of(articleEntity.status))
        )
    }
}
