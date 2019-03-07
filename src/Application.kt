package com.theorangeteam

import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

@Suppress("unused") // Referenced in application.conf
fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    installDependencies()
    defineRoutes()
}

fun Application.installDependencies() {
    install(ContentNegotiation) {
        jackson {}
    }
}

fun Application.defineRoutes() {

    routing {
        get("/") {
            call.respondText("The Proud Best Friend Forever")
        }
    }
}

