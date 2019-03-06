package com.theorangeteam

import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngineEnvironment
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

@Suppress("unused") // Referenced in application.conf
fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        installDependencies()
        defineRoutes()
    }
    server.start(wait = true)
}

fun loadEnvironmentConfiguration(): ApplicationEngineEnvironment {
    return applicationEngineEnvironment {
        connector {

        }
    }
}

fun Application.installDependencies() {
    install(ContentNegotiation) {
        jackson {}
    }
}

fun Application.defineRoutes() {

    routing {
        get("/something") {
            call.respondText("OK")
        }
    }
}

