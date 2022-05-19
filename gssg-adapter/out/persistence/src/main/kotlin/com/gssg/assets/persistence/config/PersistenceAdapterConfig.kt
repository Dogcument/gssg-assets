package com.gssg.assets.persistence.config

import com.gssg.assets.application.domain.article.port.out.ArticlePersistencePort
import com.gssg.assets.application.domain.member.port.out.MemberPersistencePort
import com.gssg.assets.application.domain.topic.base.port.out.TopicPersistencePort
import com.gssg.assets.application.domain.topic.pick.port.out.PickPersistencePort
import com.gssg.assets.persistence.domain.article.adapter.ArticlePersistencePortAdapter
import com.gssg.assets.persistence.domain.article.repository.ArticleRepository
import com.gssg.assets.persistence.domain.member.adapter.MemberPersistencePortAdapter
import com.gssg.assets.persistence.domain.member.repository.MemberRepository
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
}
