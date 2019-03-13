package com.theorangeteam.domain.user

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
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
            userController.loadUser(userID)?.let {
                call.respond(it)
            } ?: call.respond(HttpURLConnection.HTTP_NOT_FOUND)
        }
    }

    private fun Route.postRoutes() {
    }
}