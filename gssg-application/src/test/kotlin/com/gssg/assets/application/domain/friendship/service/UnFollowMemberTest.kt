package com.gssg.assets.application.domain.friendship.service

import com.gssg.assets.application.domain.friendship.port.`in`.UnFollowMemberUseCase
import com.gssg.assets.application.domain.friendship.port.out.MockFriendshipPersistencePortAdapter
import com.gssg.assets.application.domain.friendship.service.exception.FriendshipNotFoundException
import com.gssg.assets.domain.friendship.FriendshipId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

/**
 * @Author Heli
 */
internal class UnFollowMemberTest {

    private val friendshipPersistencePort = MockFriendshipPersistencePortAdapter()

    private val unFollowMember = UnFollowMember(
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
    fun `멤버를 UnFollow 할 수 있다`() {
        assertDoesNotThrow {
            unFollowMember()
        }
        assertThat(true)
    }

    @Test
    fun `존재하지 않은 Friendship 에 대해 UnFollow 하면 Exception 이 발생한다`() {
        assertThrows<FriendshipNotFoundException> {
            unFollowMember(
                existFriendshipId = FriendshipId(999L)
            )
        }
    }

    private fun unFollowMember(
        existFriendshipId: FriendshipId? = null
    ) {
        val command = UnFollowMemberUseCase.Command(
            friendshipId = existFriendshipId ?: FriendshipId(1L)
        )

        unFollowMember.command(command = command)
    }
}
