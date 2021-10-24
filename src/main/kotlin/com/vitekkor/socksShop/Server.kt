package com.vitekkor.socksShop

import com.typesafe.config.ConfigFactory
import com.vitekkor.socksShop.data.Operation
import com.vitekkor.socksShop.data.Socks
import com.vitekkor.socksShop.dataBase.addSocks
import com.vitekkor.socksShop.dataBase.connectToDataBase
import com.vitekkor.socksShop.dataBase.getSocksCount
import com.vitekkor.socksShop.dataBase.removeSocks
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.request.ContentTransformationException
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import kotlinx.serialization.SerializationException

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.socksShop() {
    val dbConfig = if (!ConfigFactory.load().getString("ktor.deployment.test").toBoolean())
        environment.config else HoconApplicationConfig(ConfigFactory.load())
    connectToDataBase(dbConfig.config("ktor.database"))
    install(ContentNegotiation) {
        json()
    }
    routing {
        post("/api/socks/income") {
            call.resolveSocksRequest(::addSocks)
        }

        post("/api/socks/outcome") {
            call.resolveSocksRequest(::removeSocks)
        }

        get("/api/socks") {
            val parameters = call.parameters.getOrNull()
            if (parameters != null)
                call.respond(
                    HttpStatusCode.OK,
                    getSocksCount(parameters.first, parameters.second, parameters.third)
                )
            else call.respond(HttpStatusCode.BadRequest)
        }
    }
}

fun Parameters.getOrNull(): Triple<String, Operation, Int>? {
    if (names() != setOf("color", "operation", "cottonPart")) return null
    val color = getAll("color")?.let {
        if (it.size > 1) return null else it.first()
    } ?: return null

    val operation = getAll("operation")?.let {
        if (it.size > 1) return null else Operation.getFromStringOrNull(it.first()) ?: return null
    } ?: return null

    val cottonPart = getAll("cottonPart")?.let {
        if (it.size > 1) return null else it.first().toIntOrNull() ?: return null
    } ?: return null

    return Triple(color, operation, cottonPart)
}

suspend fun ApplicationCall.resolveSocksRequest(resolvingFun: (Socks) -> Unit) {
    try {
        val socks = receive<Socks>()
        resolvingFun(socks)
        respond(HttpStatusCode.OK)
    } catch (e: Exception) {
        if (e is IllegalArgumentException || e is ContentTransformationException) {
            println(e.message)
            respond(HttpStatusCode.BadRequest)
        } else respond(HttpStatusCode.InternalServerError)
    }
}

