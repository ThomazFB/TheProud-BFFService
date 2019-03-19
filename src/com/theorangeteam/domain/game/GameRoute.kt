package com.theorangeteam.domain.game

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.routing.*
import java.net.HttpURLConnection

class GameRoute(application: Application) {

    private val gameController by lazy { GameController() }

    init {
        application.routing {
            route("game", HttpMethod.Get) {
                gameGetRoutes()
            }
        }
    }

    private fun Route.gameGetRoutes() {
        get("/") {
            gameController.loadGames()?.let {
                call.respond(it)
            } ?: call.respond(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }

        get("/{gameid}") {
            val gameID = Integer.valueOf(call.parameters["gameid"])
            gameController.loadSingleGame(gameID)?.let {
                call.respond(it)
            } ?: call.respond(HttpURLConnection.HTTP_NOT_FOUND)
        }
    }
}