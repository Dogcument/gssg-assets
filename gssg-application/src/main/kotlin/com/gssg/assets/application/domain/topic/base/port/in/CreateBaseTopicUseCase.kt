package com.gssg.assets.application.domain.topic.base.port.`in`

import com.gssg.assets.domain.topic.base.Topic

/**
 * @Author Heli
 */
interface CreateBaseTopicUseCase {

    fun command(command: Command)

    data class Command(
        val topic: Topic
    )
}
