package com.vitekkor.socksShop

import com.vitekkor.socksShop.data.Operation
import com.vitekkor.socksShop.data.Socks
import com.vitekkor.socksShop.dataBase.SocksTable
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.opentest4j.AssertionFailedError
import java.util.*
import kotlin.random.Random

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ServerKtTest {

    companion object {
        val incomingCopy = mutableListOf<Socks>()
        val incomingSocks = mutableListOf<Socks>().apply {
            for (i in 1..500) {
                val socks =
                    Socks("${Random.nextInt(0, 101)}", Random.nextInt(0, 101), Random.nextLong(0, 1000_000))
                add(socks)
                incomingCopy.add(socks)
            }
        }
    }

    private val db = Database.connect("jdbc:h2:mem://test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

    @Test
    @Order(6)
    fun clearDataBase() {
        transaction(db) {
            SchemaUtils.drop(SocksTable)
        }
        TransactionManager.closeAndUnregister(db)
    }

    @Test
    @BeforeEach
    fun createDataBase() {
        transaction(db) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(SocksTable)
        }
    }

    @Test
    @Order(1)
    @kotlinx.serialization.ExperimentalSerializationApi
    fun testPostApiSocksIncome() {
        val badReq = listOf(
            "{}",
            "{color: , cottonPart: 42, quantity: 100}",
            "{cottonPart: 42, quantity: 100}",
            "{color: red, cottonPart: 42, quantity: 100, field: value}",
            "{color: color, cottonPart: cottonPart, quantity: quantity}",
            "{color: color, cottonPart: 146, quantity: 50}",
            "{color: color, cottonPart: 42, quantity: -250}",
        )
        withTestApplication({ socksShop() }) {
            for (bad in badReq) {
                handleJsonRequest(bad, "/api/socks/income").apply {
                    assertEquals(HttpStatusCode.BadRequest, response.status())
                }
            }
            for (socks in incomingSocks) {
                val body = Json.encodeToString(socks)
                val before = transaction(db) {
                    SocksTable.select { (SocksTable.color eq socks.color) and (SocksTable.cottonPart eq socks.cottonPart) }
                        .firstOrNull()?.get(SocksTable.quantity) ?: 0L
                }
                handleJsonRequest(body, "/api/socks/income").apply {
                    assertEquals(HttpStatusCode.OK, response.status())
                    val after = transaction(db) {
                        SocksTable.select { (SocksTable.color eq socks.color) and (SocksTable.cottonPart eq socks.cottonPart) }
                            .firstOrNull()?.get(SocksTable.quantity) ?: throw AssertionFailedError()
                    }
                    assertEquals(after - before, socks.quantity)
                }
            }
        }
    }

    @Test
    @Order(2)
    fun testGetApiSocks() {
        withTestApplication({ socksShop() }) {
            val badReqs = listOf(
                "",
                "color=red&operation=more&cottonPart=90",
                "color=blue&operation=equal&cottonPart=",
                "operation=moreThan&cottonPart=90",
                "color=white&operation=lessThan&cottonPart=90&operation=equal",
                "color=red&operation=EQUAL&cottonPart=90",
                "color=col&operation=equal&cottonPart=one",
                "color=red&operation=moreThan&cottonPart=90&someParameter=someValue"
            )
            for (badReq in badReqs) {
                handleRequest(HttpMethod.Get, "/api/socks?$badReq").apply {
                    assertEquals(HttpStatusCode.BadRequest, response.status())
                }
            }
            for (i in 1..500) {
                val color = incomingSocks[Random.nextInt(0, incomingSocks.size)].color
                val cottonPart = incomingSocks[Random.nextInt(0, incomingSocks.size)].cottonPart
                val operation = Operation.values()[Random.nextInt(0, 3)]
                    .toString()
                    .lowercase(Locale.getDefault())
                    .replace("_t", "T")
                val reqUrl = "/api/socks?color=$color&operation=$operation&cottonPart=$cottonPart"
                handleRequest(HttpMethod.Get, reqUrl).apply {
                    val res = incomingSocks.filter {
                        it.color == color && when (operation) {
                            "lessThan" -> it.cottonPart < cottonPart
                            "moreThan" -> it.cottonPart > cottonPart
                            else -> it.cottonPart == cottonPart
                        }
                    }.fold(0L) { acc, socks -> acc + socks.quantity }
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals(res, response.content!!.toLong())
                }
            }
        }
    }

    @Test
    @Order(3)
    @kotlinx.serialization.ExperimentalSerializationApi
    fun testPostApiSocksOutcome() {
        val uri = "/api/socks/outcome"
        withTestApplication({ socksShop() }) {
            while (incomingSocks.isNotEmpty()) {
                val socks = incomingSocks.first()
                val body = Json.encodeToString(socks)
                if (Random.nextBoolean()) {
                    val bad = socks.copy(quantity = socks.quantity + Random.nextInt(0, 100))
                    val realQuantity = incomingSocks.filter {
                        it.color == socks.color && it.cottonPart == socks.cottonPart
                    }.fold(0L) { a, s -> a + s.quantity }
                    handleJsonRequest(Json.encodeToString(bad), uri).apply {
                        if (realQuantity >= bad.quantity) {
                            assertEquals(HttpStatusCode.OK, response.status())
                            handleJsonRequest(Json.encodeToString(bad), "/api/socks/income").apply {
                                assertEquals(HttpStatusCode.OK, response.status())
                            }
                        } else {
                            assertEquals(HttpStatusCode.BadRequest, response.status())
                        }
                    }
                }
                handleJsonRequest(body, uri).apply {
                    assertEquals(HttpStatusCode.OK, response.status())
                }
                incomingSocks.remove(socks)
            }
        }
    }

    @Test
    @Order(4)
    fun testAfterPostApiSocksOutcome() {
        withTestApplication({ socksShop() }) {
            for (socks in incomingCopy) {
                with(socks) {
                    handleRequest(
                        HttpMethod.Get,
                        "/api/socks?color=$color&operation=equal&cottonPart=$cottonPart"
                    ).apply {
                        assertEquals(HttpStatusCode.OK, response.status())
                        assertEquals(0L, response.content!!.toLong())
                    }
                }

            }
        }
    }

    private fun TestApplicationEngine.handleJsonRequest(json: String, uri: String): TestApplicationCall {
        return handleRequest(HttpMethod.Post, uri) {
            addHeader(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
            setBody(json)
        }
    }
}