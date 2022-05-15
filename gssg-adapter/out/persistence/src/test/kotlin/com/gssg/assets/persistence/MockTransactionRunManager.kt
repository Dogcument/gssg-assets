package com.gssg.assets.persistence

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @Author Heli
 */
abstract class MockTransactionRunManager(
    private vararg val tables: Table,
    private val initStatement: Transaction.() -> Unit
) {
    init {
        Database.connect(
            "jdbc:h2:mem:test",
            driver = "org.h2.Driver",
            user = "root",
            password = ""
        )
    }

    internal fun <T> runTransaction(statement: Transaction.() -> T) {
        transaction {
            SchemaUtils.create(*tables)
            initStatement()
            statement()
            SchemaUtils.drop(*tables)
        }
    }
}
