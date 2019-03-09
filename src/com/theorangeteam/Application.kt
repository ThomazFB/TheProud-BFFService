package com.theorangeteam

import game.GameRoute
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.module() {
    IGDBWrapper.userkey = System.getenv("IGDB_KEY")
    installDependencies()
    defineRoutes()
}

fun Application.installDependencies() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
}

fun Application.defineRoutes() {

    routing {
        get("/") {
            call.respondText("The Proud Best Friend Forever")
        }
    }
    GameRoute(this)
}

