package com.gssg.assets.application.domain.reaction.comment.port.`in`

import com.gssg.assets.domain.reaction.comment.CommentReactionId

/**
 * @Author Heli
 */
interface CancelCommentReactionUseCase {

    fun command(command: Command)

    data class Command(
        val commentReactionId: CommentReactionId
    )
}
