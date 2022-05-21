package com.gssg.assets.application.domain.comment.port.`in`

import com.gssg.assets.domain.comment.Comment

/**
 * @Author Heli
 */
interface WriteCommentUseCase {

    fun command(command: Command)

    data class Command(
        val comment: Comment
    )
}
