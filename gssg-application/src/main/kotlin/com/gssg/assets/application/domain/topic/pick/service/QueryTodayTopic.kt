package com.gssg.assets.application.domain.topic.pick.service

import com.gssg.assets.application.domain.topic.pick.port.`in`.QueryTodayTopicUseCase
import com.gssg.assets.application.domain.topic.pick.port.out.PickPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class QueryTodayTopic(
    private val pickPersistencePort: PickPersistencePort
) : QueryTodayTopicUseCase {

    private val logger = logger()

    override fun query(query: QueryTodayTopicUseCase.Query): QueryTodayTopicUseCase.Result {
        val targetDate = query.targetDate

        logger.info("토픽 피커를 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        val picks = pickPersistencePort.findByTargetDate(
            pickTargetDate = targetDate
        )

        return QueryTodayTopicUseCase.Result(
            pickTopics = picks
        )
    }
}
