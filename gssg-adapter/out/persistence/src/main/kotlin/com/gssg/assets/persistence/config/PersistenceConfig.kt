package com.gssg.assets.persistence.config

import com.gssg.assets.persistence.domain.article.repository.ArticleRepository
import com.gssg.assets.persistence.domain.article.repository.ArticleRepositoryImpl
import com.gssg.assets.persistence.domain.comment.repository.CommentRepository
import com.gssg.assets.persistence.domain.comment.repository.CommentRepositoryImpl
import com.gssg.assets.persistence.domain.member.repository.MemberRepository
import com.gssg.assets.persistence.domain.member.repository.MemberRepositoryImpl
import com.gssg.assets.persistence.domain.reaction.article.repository.ArticleReactionRepository
import com.gssg.assets.persistence.domain.reaction.article.repository.ArticleReactionRepositoryImpl
import com.gssg.assets.persistence.domain.topic.base.repository.TopicRepository
import com.gssg.assets.persistence.domain.topic.base.repository.TopicRepositoryImpl
import com.gssg.assets.persistence.domain.topic.pick.repository.PickRepository
import com.gssg.assets.persistence.domain.topic.pick.repository.PickRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @Author Heli
 */
@Configuration
class PersistenceConfig {

    @Bean
    fun memberRepository(): MemberRepository {
        return MemberRepositoryImpl()
    }

    @Bean
    fun topicRepository(): TopicRepository {
        return TopicRepositoryImpl()
    }

    @Bean
    fun pickRepository(): PickRepository {
        return PickRepositoryImpl()
    }

    @Bean
    fun articleRepository(): ArticleRepository {
        return ArticleRepositoryImpl()
    }

    @Bean
    fun commentRepository(): CommentRepository {
        return CommentRepositoryImpl()
    }

    @Bean
    fun articleReactionRepository(): ArticleReactionRepository {
        return ArticleReactionRepositoryImpl()
    }
}
