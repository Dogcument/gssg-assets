package com.gssg.assets.persistence.domain.friendship.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.friendship.entity.FriendshipEntities
import com.gssg.assets.persistence.domain.friendship.entity.FriendshipEntity
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * @Author Heli
 */
class FriendshipRepositoryImpl : FriendshipRepository,
    CommonRepository<Long, FriendshipEntities>(FriendshipEntities) {

    override fun insert(definition: FriendshipRepository.FriendShipDefinition) {
        execInsert {
            insertOrUpdate(it, definition)
        }
    }

    override fun update(id: Long, definition: FriendshipRepository.FriendShipDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, definition)
        }
    }

    private fun FriendshipEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        definition: FriendshipRepository.FriendShipDefinition
    ) {
        it[type] = definition.type.name
        it[status] = definition.status.name
        it[fromMemberId] = definition.fromMemberId
        it[toMemberId] = definition.toMemberId
    }

    override fun findById(id: Long): FriendshipEntity? {
        return queryById(id = id) {
            FriendshipEntity.wrapRow(it)
        }
    }

    override fun findByFromMemberId(fromMemberId: Long): List<FriendshipEntity> {
        return FriendshipEntities
            .select { FriendshipEntities.fromMemberId eq fromMemberId }
            .map { FriendshipEntity.wrapRow(it) }
    }
}
