package com.gssg.assets.application.domain.friendship.service

import com.gssg.assets.application.domain.friendship.port.`in`.UnFollowMemberUseCase
import com.gssg.assets.application.domain.friendship.port.out.FriendshipPersistencePort
import com.gssg.assets.application.domain.friendship.service.exception.FriendshipNotFoundException
import com.gssg.assets.domain.logger
import com.gssg.assets.domain.member.MemberId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class UnFollowMember(
    private val friendshipPersistencePort: FriendshipPersistencePort
) : UnFollowMemberUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: UnFollowMemberUseCase.Command) {
        val friendshipId = command.friendshipId

        val friendship = friendshipPersistencePort.findBy(id = friendshipId)
            ?: throw FriendshipNotFoundException(friendshipId = friendshipId)

        // TODO Authentication 정보 가져와 검증 필요
        val temporaryRequestMemberId = MemberId(1L)
        logger.info("Friendship 을 데이터베이스에 반영하기 위해 영속성 포트 호출 [Friendship.Id=${friendship.id.id}]")
        friendshipPersistencePort.update(friendship.unfollow(temporaryRequestMemberId))
    }
}
