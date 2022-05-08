package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.ModifyMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MemberPersistencePort
import com.gssg.assets.application.domain.member.service.exception.MemberNotFoundException
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class ModifyMember(
    private val memberPersistencePort: MemberPersistencePort
) : ModifyMemberUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: ModifyMemberUseCase.Command) {
        val memberId = command.memberId

        logger.info("멤버를 데이베이스에서 조회하기 위해 영속성 포트를 호출")
        val member = memberPersistencePort.findById(memberId = memberId)
            ?: throw MemberNotFoundException(memberId = memberId)

        logger.info("멤버를 수정하기 위해 영속성 포트를 호출")
        val modifiedMember = member.update(
            newDisplayName = command.memberDisplayName,
            newIntroduce = command.memberIntroduce,
            newProfileDog = command.memberProfileDog
        )

        logger.info("멤버를 데이터베이스에 저장하기 위해 영속성 포트를 호출")
        memberPersistencePort.update(modifiedMember)
    }
}
