package com.theorangeteam.domain.user

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.*
import java.net.HttpURLConnection

class UserRoute(application: Application) {

    private val userController by lazy { UserController() }

    init {
        application.routing {
            route("user") {
                postRoutes()
            }
        }
    }

    private fun Route.postRoutes() {
        post("token") {
            call.receiveParameters()["uid"]?.let { userID ->
                userController.createUserToken(userID)?.let { token ->
                    call.respond(token)
                } ?: call.respond(HttpURLConnection.HTTP_BAD_REQUEST)
            } ?: call.respond(HttpURLConnection.HTTP_BAD_METHOD)
        }

        post("create") {
            val postParameters = call.receiveParameters()
        }
    }
}