package com.gssg.assets.persistence.config

import com.gssg.assets.application.port.out.MemberPersistencePort
import com.gssg.assets.persistence.domain.member.adapter.MemberPersistencePortAdapter
import com.gssg.assets.persistence.domain.member.repository.MemberRepository
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
}
