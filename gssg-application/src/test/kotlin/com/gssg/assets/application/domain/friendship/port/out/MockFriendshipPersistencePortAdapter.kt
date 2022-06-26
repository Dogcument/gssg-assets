package com.gssg.assets.application.domain.friendship.port.out

import com.gssg.assets.domain.friendship.*
import com.gssg.assets.domain.friendship.enums.Status
import com.gssg.assets.domain.friendship.enums.Type
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
internal class MockFriendshipPersistencePortAdapter : FriendshipPersistencePort {

    private val db = mutableMapOf<FriendshipId, Friendship>()
    private val distributedId = AtomicLong(1L)

    override fun insert(friendship: Friendship) {
        val id = FriendshipId(id = distributedId.getAndIncrement())
        db[id] = friendship
    }

    override fun update(friendship: Friendship) {
        val id = FriendshipId(id = friendship.longId)
        db[id] = friendship
    }

    override fun findBy(id: FriendshipId): Friendship? {
        return db[id]
    }

    override fun findBy(fromMemberId: FriendshipFromMemberId): List<Friendship> {
        return db.values.filter {
            it.fromMemberId.fromMemberId == fromMemberId.fromMemberId
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        (1L..3L).forEach { longId ->
            db[FriendshipId(id = longId)] = Friendship(
                id = FriendshipId(id = longId),
                createdAt = FriendshipCreatedAt(createdAt = now),
                modifiedAt = FriendshipModifiedAt(modifiedAt = now),
                type = FriendshipType(Type.FOLLOW),
                status = FriendshipStatus(Status.ACTIVE),
                fromMemberId = FriendshipFromMemberId(1L),
                toMemberId = FriendshipToMemberId(longId + 10L)
            )
        }
    }

    fun clear() {
        db.clear()
    }
}
