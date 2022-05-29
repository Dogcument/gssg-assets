package com.gssg.assets.application.domain.reaction.article.service.exception

import com.gssg.assets.domain.reaction.article.ArticleReactionId

/**
 * @Author Heli
 */
class ArticleReactionNotFoundException(
    override val message: String?
) : RuntimeException() {
    constructor(articleReactionId: ArticleReactionId) : this("게시글 리액션 객체를 찾지 못했습니다 [articleReactionId=${articleReactionId.id}]")
}
