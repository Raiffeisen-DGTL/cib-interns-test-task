package com.vitekkor.socksShop.dataBase

import com.vitekkor.socksShop.data.Operation
import com.vitekkor.socksShop.data.Socks
import io.ktor.config.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

private lateinit var database: Database


fun connectToDataBase(dbConfig: ApplicationConfig) {
    val driverClass = dbConfig.property("driverClass").getString()
    val url = dbConfig.property("url").getString()
    val user = dbConfig.property("user").getString()
    val password = dbConfig.property("password").getString()
    val type = dbConfig.propertyOrNull("type")?.getString() ?: "postgresql"
    database = Database.connect(
        "jdbc:$type://$url", driver = driverClass,
        user = user, password = password
    )
}

fun addSocks(socks: Socks) {
    dbQuery {
        val inTable =
            SocksTable.select { (SocksTable.color eq socks.color) and (SocksTable.cottonPart eq socks.cottonPart) }
                .firstOrNull() != null
        if (inTable) {
            SocksTable.update({ (SocksTable.color eq socks.color) and (SocksTable.cottonPart eq socks.cottonPart) }) {
                with(SqlExpressionBuilder) {
                    it[quantity] = quantity + socks.quantity
                }
            }
        } else {
            SocksTable.insert {
                it[color] = socks.color
                it[cottonPart] = socks.cottonPart
                it[quantity] = socks.quantity
            }
        }
    }
}

fun removeSocks(socks: Socks) {
    dbQuery {
        val inTable =
            SocksTable.select { (SocksTable.color eq socks.color) and (SocksTable.cottonPart eq socks.cottonPart) }
                .firstOrNull() ?: throw IllegalArgumentException()
        if (inTable[SocksTable.quantity] >= socks.quantity)
            SocksTable.update({ (SocksTable.color eq socks.color) and (SocksTable.cottonPart eq socks.cottonPart) }) {
                with(SqlExpressionBuilder) {
                    it[quantity] = quantity - socks.quantity
                }
            }
        else throw IllegalArgumentException()
    }
}

fun getSocksCount(color: String, operation: Operation, cottonPart: Int): Long {
    return dbQuery {
        val op = when (operation) {
            Operation.MORE_THAN -> Op.build { SocksTable.cottonPart greater cottonPart }
            Operation.LESS_THAN -> Op.build { SocksTable.cottonPart less cottonPart }
            else -> Op.build { SocksTable.cottonPart eq cottonPart }
        }
        SocksTable.select { (SocksTable.color eq color) and (op) }
            .fold(0L) { count, socks -> count + socks[SocksTable.quantity] }
    }
}

private fun <T> dbQuery(block: () -> T): T {
    return transaction(database) {
        addLogger(StdOutSqlLogger)
        block()
    }
}

object SocksTable : Table() {
    val color = text("color")
    val cottonPart = integer("cotton_part")
    val quantity = long("quantity")
}