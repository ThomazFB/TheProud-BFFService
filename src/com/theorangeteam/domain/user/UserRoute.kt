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
                getRoutes()
                postRoutes()
            }
        }
    }

    private fun Route.getRoutes() {
        get("/{uid}") {
            val userID = call.parameters["uid"].toString()
            userController.login(userID)?.let {
                call.respond(it)
            } ?: call.respond(HttpURLConnection.HTTP_NOT_FOUND)
        }
    }

    private fun Route.postRoutes() {
        post("token") {
            call.receiveParameters()["uid"]?.let { userID ->
                userController.generateToken(userID)?.let { token ->
                    call.respond(token)
                } ?: call.respond(HttpURLConnection.HTTP_BAD_REQUEST)
            } ?: call.respond(HttpURLConnection.HTTP_BAD_REQUEST)

        }
        post("create") {
            val postParameters = call.receiveParameters()
        }
    }
}