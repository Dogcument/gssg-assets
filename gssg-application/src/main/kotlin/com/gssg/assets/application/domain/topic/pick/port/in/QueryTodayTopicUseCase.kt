package com.gssg.assets.application.domain.topic.pick.port.`in`

import com.gssg.assets.domain.topic.pick.Pick
import com.gssg.assets.domain.topic.pick.PickTargetDate

/**
 * @Author Heli
 */
interface QueryTodayTopicUseCase {

    fun query(query: Query): Result

    data class Query(
        val targetDate: PickTargetDate
    )

    data class Result(
        val pickTopics: List<Pick>
    )
}
