package com.gssg.assets.application.domain.article.service

import com.gssg.assets.application.domain.article.port.`in`.WriteArticleUseCase
import com.gssg.assets.application.domain.article.port.out.ArticlePersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class WriteArticle(
    private val articlePersistencePort: ArticlePersistencePort
) : WriteArticleUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: WriteArticleUseCase.Command) {
        val article = command.article

        logger.info("게시글을 데이터베이스에 저장하기 위해 영속성 포트 호출 [Article.Title=${article.title.title}}]")
        articlePersistencePort.insert(article)
    }
}
