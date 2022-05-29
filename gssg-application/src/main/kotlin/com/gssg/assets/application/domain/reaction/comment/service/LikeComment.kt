package com.gssg.assets.application.domain.reaction.comment.service

import com.gssg.assets.application.domain.reaction.comment.port.`in`.LikeCommentUseCase
import com.gssg.assets.application.domain.reaction.comment.port.out.CommentReactionPersistencePort
import com.gssg.assets.domain.logger
import com.gssg.assets.domain.reaction.comment.CommentReaction
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class LikeComment(
    private val commentReactionPersistencePort: CommentReactionPersistencePort
) : LikeCommentUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: LikeCommentUseCase.Command) {
        val (reactorId, targetId) = command

        val commentReaction = CommentReaction.like(
            reactorId = reactorId,
            targetId = targetId
        )

        logger.info("댓글 리액션을 데이터베이스에 저장하기 위해 영속성 포트 호출 [reactorId=${reactorId.reactorId},targetId=${targetId.targetId}]")
        commentReactionPersistencePort.insert(commentReaction)
    }
}
