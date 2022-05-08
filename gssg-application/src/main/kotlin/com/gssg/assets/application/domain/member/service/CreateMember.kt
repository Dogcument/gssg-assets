package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.CreateMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MemberPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class CreateMember(
    private val memberPersistencePort: MemberPersistencePort
) : CreateMemberUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: CreateMemberUseCase.Command) {
        val member = command.member

        logger.info("멤버를 데이터베이스에 저장하기 위해 영속성 포트 호출 [Member.Email=${member.email.email}]")
        memberPersistencePort.insert(member)
    }
}
