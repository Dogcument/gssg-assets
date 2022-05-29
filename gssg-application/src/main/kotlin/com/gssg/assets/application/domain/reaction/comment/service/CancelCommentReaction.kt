package com.gssg.assets.application.domain.reaction.comment.service

import com.gssg.assets.application.domain.reaction.comment.port.`in`.CancelCommentReactionUseCase
import com.gssg.assets.application.domain.reaction.comment.port.out.CommentReactionPersistencePort
import com.gssg.assets.application.domain.reaction.comment.service.exception.CommentReactionNotFoundException
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class CancelCommentReaction(
    private val commentReactionPersistencePort: CommentReactionPersistencePort
) : CancelCommentReactionUseCase {

    private val logger = logger()

    override fun command(command: CancelCommentReactionUseCase.Command) {
        val commentReactionId = command.commentReactionId

        logger.info("댓글 리액션을 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        val commentReaction = commentReactionPersistencePort.findBy(
            commentReactionId = commentReactionId
        ) ?: throw CommentReactionNotFoundException(commentReactionId = commentReactionId)

        val canceledCommentReaction = commentReaction.cancel()

        logger.info("댓글 리액션을 데이터베이스에 저장하기 위해 양속성 포트를 호출")
        commentReactionPersistencePort.update(canceledCommentReaction)
    }
}
