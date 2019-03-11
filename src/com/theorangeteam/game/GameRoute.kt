package com.theorangeteam.game

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.*
import java.net.HttpURLConnection

class GameRoute(application: Application) {

    val gameController by lazy { GameController() }

    init {
        application.routing {
            route("games") {
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
            gameController.loadSingleGame(Integer.valueOf(call.parameters["gameid"]))?.let {
                call.respond(it)
            } ?: call.respond(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }
    }
}