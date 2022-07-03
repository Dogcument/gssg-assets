package com.gssg.assets.application.domain.friendship.service

import com.gssg.assets.application.domain.friendship.port.`in`.FollowMemberUseCase
import com.gssg.assets.application.domain.friendship.port.out.FriendshipPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class FollowMember(
    private val friendshipPersistencePort: FriendshipPersistencePort
) : FollowMemberUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: FollowMemberUseCase.Command) {
        val friendship = command.friendship

        logger.info("Friendship 을 데이터베이스에 저장하기 위해 영속성 포트 호출 [Friendship.toMemberId=${friendship.toMemberId.toMemberId}, Friendship.fromMemberId=${friendship.fromMemberId.fromMemberId}]")
        friendshipPersistencePort.insert(friendship)
    }
}
