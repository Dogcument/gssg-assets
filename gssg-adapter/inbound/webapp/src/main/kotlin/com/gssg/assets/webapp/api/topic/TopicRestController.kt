package com.gssg.assets.webapp.api.topic

import com.gssg.assets.application.domain.topic.base.port.`in`.CreateBaseTopicUseCase
import com.gssg.assets.application.domain.topic.pick.port.`in`.QueryTodayTopicUseCase
import com.gssg.assets.domain.topic.pick.PickTargetDate
import com.gssg.assets.webapp.api.topic.mapper.CreateBaseTopicRequest
import com.gssg.assets.webapp.api.topic.mapper.QueryTodayTopicResponse
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

/**
 * @Author Heli
 */
@RestController
class TopicRestController(
    private val queryTodayTopicUseCase: QueryTodayTopicUseCase,
    private val createBaseTopicUseCase: CreateBaseTopicUseCase
) {

    @GetMapping("/api/v1/topic/today")
    fun queryTopicOfDate(
        @RequestParam(required = false)
        targetDate: LocalDate = LocalDate.now()
    ): List<QueryTodayTopicResponse> {
        val pickTargetDate = PickTargetDate(targetDate)
        val query = QueryTodayTopicUseCase.Query(targetDate = pickTargetDate)

        val result = queryTodayTopicUseCase.query(query)
        return result.pickTopics.map(QueryTodayTopicResponse::from)
    }

    @PostMapping("/api/v1/topic")
    fun createBaseTopic(
        @RequestBody request: CreateBaseTopicRequest
    ) {
        val command = request.toCreateUseCaseCommand()
        createBaseTopicUseCase.command(command = command)
    }
}
