package com.gssg.assets.application.domain.friendship.service

import com.gssg.assets.application.domain.friendship.port.`in`.FollowMemberUseCase
import com.gssg.assets.application.domain.friendship.port.out.MockFriendshipPersistencePortAdapter
import com.gssg.assets.domain.friendship.Friendship
import com.gssg.assets.domain.friendship.FriendshipFromMemberId
import com.gssg.assets.domain.friendship.FriendshipToMemberId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * @Author Heli
 */
internal class FollowMemberTest {

    private val friendshipPersistencePort = MockFriendshipPersistencePortAdapter()

    private val followMember = FollowMember(
        friendshipPersistencePort = friendshipPersistencePort
    )

    @BeforeEach
    fun init() {
        friendshipPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        friendshipPersistencePort.clear()
    }

    @Test
    fun `멤버를 Follow 할 수 있다`() {
        assertDoesNotThrow {
            followMember()
        }
        assertThat(true)
    }

    private fun followMember() {
        val command = FollowMemberUseCase.Command(
            friendship = Friendship.follow(
                fromMemberId = FriendshipFromMemberId(fromMemberId = 1L),
                toMemberId = FriendshipToMemberId(toMemberId = 2L)
            )
        )
        followMember.command(command = command)
    }
}
