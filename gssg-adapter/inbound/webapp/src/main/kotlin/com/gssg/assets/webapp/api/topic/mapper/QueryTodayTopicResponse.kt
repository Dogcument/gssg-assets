package com.gssg.assets.webapp.api.topic.mapper

import com.gssg.assets.domain.topic.pick.Pick

/**
 * @Author Heli
 */
data class QueryTodayTopicResponse(
    val topicId: Long,
    val text: String,
    val description: String
) {

    companion object {
        fun from(
            pick: Pick
        ) = QueryTodayTopicResponse(
            topicId = pick.topic.topic.longId,
            text = pick.topic.topic.text.text,
            description = pick.topic.topic.description.description
        )
    }
}
