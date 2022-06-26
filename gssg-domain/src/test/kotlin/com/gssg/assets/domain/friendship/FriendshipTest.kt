package com.gssg.assets.domain.friendship

import com.gssg.assets.domain.friendship.enums.Type
import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.domain.member.enums.Status
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class FriendshipTest {

    companion object {
        private const val DEFAULT_FOLLOW_TO_MEMBER_ID = 1L
        private const val DEFAULT_FOLLOW_FROM_MEMBER_ID = 2L
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val actual = followFriendship()

        Assertions.assertThat(actual.longId).isEqualTo(-1L)
        Assertions.assertThat(actual.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(actual.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Follow 시 값이 의도대로 세팅되어 있다`() {
        val actual = followFriendship()

        Assertions.assertThat(actual.fromMemberId.fromMemberId).isEqualTo(DEFAULT_FOLLOW_FROM_MEMBER_ID)
        Assertions.assertThat(actual.toMemberId.toMemberId).isEqualTo(DEFAULT_FOLLOW_TO_MEMBER_ID)
        Assertions.assertThat(actual.type.type.name).isEqualTo(Type.FOLLOW.name)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Follow 시 fromMemberId와 toMemberId는 같을 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Friendship.follow(
                fromMemberId = FriendshipFromMemberId(99L),
                toMemberId = FriendshipToMemberId(99L),
            )
        }
    }

    @Test
    fun `UnFollow 시 Status 만 변경되고 그 외 값은 그대로다`() {
        val friendship = followFriendship()

        val actual = friendship.unfollow(MemberId(DEFAULT_FOLLOW_FROM_MEMBER_ID))

        Assertions.assertThat(actual.fromMemberId.fromMemberId).isEqualTo(DEFAULT_FOLLOW_FROM_MEMBER_ID)
        Assertions.assertThat(actual.toMemberId.toMemberId).isEqualTo(DEFAULT_FOLLOW_TO_MEMBER_ID)
        Assertions.assertThat(actual.type.type.name).isEqualTo(Type.FOLLOW.name)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.INACTIVE.name)
    }

    @Test
    fun `UnFollow 시 요청한 사용자의 ID 와 fromMemberId 가 일치해야 한다`() {
        val friendship = followFriendship()

        assertThrows<IllegalArgumentException> {
            friendship.unfollow(MemberId(9999L))
        }
    }

    private fun followFriendship() = Friendship.follow(
        fromMemberId = FriendshipFromMemberId(DEFAULT_FOLLOW_FROM_MEMBER_ID),
        toMemberId = FriendshipToMemberId(DEFAULT_FOLLOW_TO_MEMBER_ID)
    )
}
