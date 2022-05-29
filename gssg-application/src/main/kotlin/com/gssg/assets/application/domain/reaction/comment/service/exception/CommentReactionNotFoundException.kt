package com.gssg.assets.application.domain.reaction.comment.service.exception

import com.gssg.assets.domain.reaction.comment.CommentReactionId


/**
 * @Author Heli
 */
class CommentReactionNotFoundException(
    override val message: String?
) : RuntimeException() {
    constructor(commentReactionId: CommentReactionId) : this("댓글 리액션 객체를 찾지 못했습니다 [commentReactionId=${commentReactionId.id}]")
}
