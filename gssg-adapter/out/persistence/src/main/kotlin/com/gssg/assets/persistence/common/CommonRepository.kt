package com.gssg.assets.persistence.common

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime

/**
 * @Author Heli
 */
abstract class CommonRepository<
        ID : Comparable<ID>,
        TABLE : CommonLongIdEntityTable>(
    private val entityTable: TABLE
) {

    fun execInsert(body: TABLE.(InsertStatement<Number>) -> Unit) {
        val now = LocalDateTime.now()
        entityTable.insert {
            it[createdAt] = now
            it[modifiedAt] = now
            body(it)
        }
    }

    fun execUpdate(id: Long, body: TABLE.(UpdateStatement) -> Unit) {
        val now = LocalDateTime.now()
        entityTable.update({ entityTable.id eq id }) {
            it[modifiedAt] = now
            body(it)
        }
    }

    fun <ENTITY : Entity<ID>> queryById(
        id: Long,
        mapper: (ResultRow) -> ENTITY
    ): ENTITY? {
        return entityTable.select {
            entityTable.id eq id
        }.singleOrNull()?.let {
            mapper(it)
        }
    }
}
