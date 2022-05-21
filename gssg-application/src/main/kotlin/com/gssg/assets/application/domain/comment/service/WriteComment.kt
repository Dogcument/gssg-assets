package com.gssg.assets.application.domain.comment.service

import com.gssg.assets.application.domain.comment.port.`in`.WriteCommentUseCase
import com.gssg.assets.application.domain.comment.port.out.CommentPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class WriteComment(
    private val commentPersistencePort: CommentPersistencePort
) : WriteCommentUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: WriteCommentUseCase.Command) {
        val comment = command.comment

        logger.info("댓글을 데이터베이스에 저장하기 위해 영속성 포트 호출 [Comment.Content=${comment.content.content}]")
        commentPersistencePort.insert(comment)
    }
}
