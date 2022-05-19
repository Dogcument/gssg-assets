package com.gssg.assets.persistence

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @Author Heli
 */
abstract class ExposedRepositoryTestManager(
    private vararg val tables: Table,
    private val initStatement: (Transaction.() -> Unit)? = null
) {
    init {
        Database.connect(
            "jdbc:h2:mem:test",
            driver = "org.h2.Driver",
            user = "root",
            password = ""
        )
    }

    internal fun <T> runTestTransaction(statement: Transaction.() -> T) {
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(*tables)
            initStatement?.let { it() }
            statement()
            SchemaUtils.drop(*tables)
        }
    }
}
