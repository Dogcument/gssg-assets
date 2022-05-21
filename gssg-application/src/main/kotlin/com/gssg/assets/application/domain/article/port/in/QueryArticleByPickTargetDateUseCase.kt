package com.gssg.assets.application.domain.article.port.`in`

import com.gssg.assets.domain.article.Article
import com.gssg.assets.domain.topic.pick.PickTargetDate

/**
 * @Author Heli
 */
interface QueryArticleByPickTargetDateUseCase {

    fun query(query: Query): Result

    data class Query(
        val pickTargetDate: PickTargetDate
    )

    data class Result(
        val articles: List<Article>
    )
}
