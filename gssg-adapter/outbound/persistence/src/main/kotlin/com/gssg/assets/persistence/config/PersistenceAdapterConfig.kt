package com.gssg.assets.persistence.config

import com.gssg.assets.application.domain.article.port.out.ArticlePersistencePort
import com.gssg.assets.application.domain.comment.port.out.CommentPersistencePort
import com.gssg.assets.application.domain.friendship.port.out.FriendshipPersistencePort
import com.gssg.assets.application.domain.member.port.out.MemberPersistencePort
import com.gssg.assets.application.domain.reaction.article.port.out.ArticleReactionPersistencePort
import com.gssg.assets.application.domain.reaction.comment.port.out.CommentReactionPersistencePort
import com.gssg.assets.application.domain.topic.base.port.out.TopicPersistencePort
import com.gssg.assets.application.domain.topic.pick.port.out.PickPersistencePort
import com.gssg.assets.persistence.domain.article.adapter.ArticlePersistencePortAdapter
import com.gssg.assets.persistence.domain.article.repository.ArticleRepository
import com.gssg.assets.persistence.domain.comment.adapter.CommentPersistencePortAdapter
import com.gssg.assets.persistence.domain.comment.repository.CommentRepository
import com.gssg.assets.persistence.domain.friendship.adapter.FriendshipPersistencePortAdapter
import com.gssg.assets.persistence.domain.friendship.repository.FriendshipRepository
import com.gssg.assets.persistence.domain.member.adapter.MemberPersistencePortAdapter
import com.gssg.assets.persistence.domain.member.repository.MemberRepository
import com.gssg.assets.persistence.domain.reaction.article.adapter.ArticleReactionPersistencePortAdapter
import com.gssg.assets.persistence.domain.reaction.article.repository.ArticleReactionRepository
import com.gssg.assets.persistence.domain.reaction.comment.adapter.CommentReactionPersistencePortAdapter
import com.gssg.assets.persistence.domain.reaction.comment.repository.CommentReactionRepository
import com.gssg.assets.persistence.domain.topic.base.adapter.TopicPersistencePortAdapter
import com.gssg.assets.persistence.domain.topic.base.repository.TopicRepository
import com.gssg.assets.persistence.domain.topic.pick.adapter.PickPersistencePortAdapter
import com.gssg.assets.persistence.domain.topic.pick.repository.PickRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * @Author Heli
 */
@Configuration
@Import(PersistenceConfig::class)
class PersistenceAdapterConfig {

    @Bean
    fun memberPersistencePort(
        memberRepository: MemberRepository
    ): MemberPersistencePort {
        return MemberPersistencePortAdapter(memberRepository = memberRepository)
    }

    @Bean
    fun topicPersistencePort(
        topicRepository: TopicRepository
    ): TopicPersistencePort {
        return TopicPersistencePortAdapter(topicRepository = topicRepository)
    }

    @Bean
    fun pickPersistencePort(
        pickRepository: PickRepository
    ): PickPersistencePort {
        return PickPersistencePortAdapter(pickRepository = pickRepository)
    }

    @Bean
    fun articlePersistencePort(
        articleRepository: ArticleRepository
    ): ArticlePersistencePort {
        return ArticlePersistencePortAdapter(articleRepository = articleRepository)
    }

    @Bean
    fun commentPersistencePort(
        commentRepository: CommentRepository
    ): CommentPersistencePort {
        return CommentPersistencePortAdapter(commentRepository = commentRepository)
    }

    @Bean
    fun articleReactionPersistencePort(
        articleReactionRepository: ArticleReactionRepository
    ): ArticleReactionPersistencePort {
        return ArticleReactionPersistencePortAdapter(articleReactionRepository = articleReactionRepository)
    }

    @Bean
    fun commentReactionPersistencePort(
        commentReactionRepository: CommentReactionRepository
    ): CommentReactionPersistencePort {
        return CommentReactionPersistencePortAdapter(commentReactionRepository = commentReactionRepository)
    }

    @Bean
    fun friendshipPersistencePort(
        friendshipRepository: FriendshipRepository
    ): FriendshipPersistencePort {
        return FriendshipPersistencePortAdapter(friendshipRepository = friendshipRepository)
    }
}
