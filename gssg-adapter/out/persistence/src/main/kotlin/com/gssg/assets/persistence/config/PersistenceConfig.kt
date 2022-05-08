package com.gssg.assets.persistence.config

import com.gssg.assets.persistence.domain.member.repository.MemberRepository
import com.gssg.assets.persistence.domain.member.repository.MemberRepositoryImpl
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
}
