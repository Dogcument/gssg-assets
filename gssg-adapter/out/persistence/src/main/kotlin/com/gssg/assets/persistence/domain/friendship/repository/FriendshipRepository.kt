package com.gssg.assets.persistence.domain.friendship.repository

import com.gssg.assets.domain.friendship.enums.Status
import com.gssg.assets.domain.friendship.enums.Type
import com.gssg.assets.persistence.common.CommonDefinition
import com.gssg.assets.persistence.domain.friendship.entity.FriendshipEntity

/**
 * @Author Heli
 */
interface FriendshipRepository {

    fun insert(definition: FriendShipDefinition)

    fun update(id: Long, definition: FriendShipDefinition)

    fun findById(id: Long): FriendshipEntity?

    fun findByFromMemberId(fromMemberId: Long): List<FriendshipEntity>

    data class FriendShipDefinition(
        val type: Type,
        val status: Status,
        val fromMemberId: Long,
        val toMemberId: Long
    ) : CommonDefinition
}
