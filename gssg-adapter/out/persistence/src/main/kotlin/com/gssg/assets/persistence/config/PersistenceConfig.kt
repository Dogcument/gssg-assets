package com.gssg.assets.persistence.config

import com.gssg.assets.persistence.domain.member.repository.MemberRepository
import com.gssg.assets.persistence.domain.member.repository.MemberRepositoryImpl
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
}
