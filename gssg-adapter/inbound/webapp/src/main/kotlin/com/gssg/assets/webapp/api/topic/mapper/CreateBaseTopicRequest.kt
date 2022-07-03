package com.gssg.assets.webapp.api.topic.mapper

import com.gssg.assets.application.domain.topic.base.port.`in`.CreateBaseTopicUseCase
import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicDescription
import com.gssg.assets.domain.topic.base.TopicText

/**
 * @Author Heli
 */
data class CreateBaseTopicRequest(
    val text: String,
    val description: String
) {

    fun toCreateUseCaseCommand(): CreateBaseTopicUseCase.Command {
        return CreateBaseTopicUseCase.Command(toTopic())
    }

    private fun toTopic() = Topic.create(
        newText = TopicText(text = text),
        newDescription = TopicDescription(description = description)
    )
}
