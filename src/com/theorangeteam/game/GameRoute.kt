package game

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import java.net.HttpURLConnection

class GameRoute(application: Application) {

    val gameController by lazy { GameController() }

    init {
        application.routing {
            route("games") {
                get {
                    gameController.loadGames()?.let {
                        call.respond(it)
                    } ?: call.respond(HttpURLConnection.HTTP_INTERNAL_ERROR)
                }
                post {

                }
            }
        }
    }
}