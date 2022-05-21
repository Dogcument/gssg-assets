package com.gssg.assets.application.domain.article.service

import com.gssg.assets.application.domain.article.port.`in`.QueryArticleByPickTargetDateUseCase
import com.gssg.assets.application.domain.article.port.out.ArticlePersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class QueryArticleByPickTargetDate(
    private val articlePersistencePort: ArticlePersistencePort
) : QueryArticleByPickTargetDateUseCase {

    private val logger = logger()

    override fun query(query: QueryArticleByPickTargetDateUseCase.Query): QueryArticleByPickTargetDateUseCase.Result {
        val pickTargetDate = query.pickTargetDate

        logger.info("게시글을 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        val articles = articlePersistencePort.findByPickTargetDate(
            pickTargetDate = pickTargetDate
        )

        return QueryArticleByPickTargetDateUseCase.Result(
            articles = articles
        )
    }
}
