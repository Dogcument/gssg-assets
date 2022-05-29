package com.gssg.assets.application.domain.reaction.comment.port.`in`

import com.gssg.assets.domain.reaction.comment.CommentReactionReactorId
import com.gssg.assets.domain.reaction.comment.CommentReactionTargetId

/**
 * @Author Heli
 */
interface LikeCommentUseCase {

    fun command(command: Command)

    data class Command(
        val reactorId: CommentReactionReactorId,
        val targetId: CommentReactionTargetId
    )
}
