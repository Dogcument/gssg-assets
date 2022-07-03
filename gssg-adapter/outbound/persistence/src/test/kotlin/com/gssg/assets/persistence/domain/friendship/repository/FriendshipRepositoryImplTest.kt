package com.gssg.assets.persistence.domain.friendship.repository

import com.gssg.assets.config.utils.notNull
import com.gssg.assets.domain.friendship.enums.Status
import com.gssg.assets.domain.friendship.enums.Type
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.persistence.ExposedRepositoryTestManager
import com.gssg.assets.persistence.domain.friendship.entity.FriendshipEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import org.assertj.core.api.Assertions
import org.jetbrains.exposed.sql.insert
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class FriendshipRepositoryImplTest : ExposedRepositoryTestManager(
    tables = arrayOf(MemberEntities, FriendshipEntities),
    initStatement = {
        val now = LocalDateTime.now()
        MemberEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[email] = "heli@example.com"
            it[password] = "password"
            it[displayName] = "Heli"
            it[introduce] = "Hi I am heli"
            it[profileDog] = ProfileDogType.ALEX.name
            it[role] = Role.ADMIN.name
            it[status] = com.gssg.assets.domain.member.enums.Status.ACTIVE.name
        }
        MemberEntities.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            it[email] = "test@example.com"
            it[password] = "password"
            it[displayName] = "test"
            it[introduce] = "Hi I am heli"
            it[profileDog] = ProfileDogType.ALEX.name
            it[role] = Role.USER.name
            it[status] = com.gssg.assets.domain.member.enums.Status.ACTIVE.name
        }
    }
) {

    private val friendshipRepository = FriendshipRepositoryImpl()

    @Test
    fun `Friendship 을 데이터베이스 INSERT 할 수 있다`() {
        val definition = FriendshipRepository.FriendShipDefinition(
            type = Type.FOLLOW,
            status = Status.ACTIVE,
            fromMemberId = 1L,
            toMemberId = 2L
        )

        runTestTransaction {
            friendshipRepository.insert(definition = definition)
            val actual = friendshipRepository.findById(1L)
            Assertions.assertThat(actual).notNull()
            Assertions.assertThat(actual!!.id.value).isEqualTo(1L)
            Assertions.assertThat(actual.fromMember.id.value).isEqualTo(1L)
            Assertions.assertThat(actual.toMember.id.value).isEqualTo(2L)
            Assertions.assertThat(actual.type).isEqualTo(Type.FOLLOW.name)
            Assertions.assertThat(actual.status).isEqualTo(Status.ACTIVE.name)
        }
    }
}
