package com.theorangeteam

import IGDBWrapper
import com.theorangeteam.api.firebase.FirebaseConfiguration
import com.theorangeteam.domain.game.GameRoute
import com.theorangeteam.domain.user.User
import com.theorangeteam.domain.user.UserController
import com.theorangeteam.domain.user.UserRoute
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.parseAuthorizationHeader
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.jetty.EngineMain
import io.ktor.util.pipeline.PipelineContext
import java.net.HttpURLConnection


class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EngineMain.main(args)
    }
}

fun Application.module() {
    IGDBWrapper.userkey = "8f52610dc7ea2f5c8918634bd87fbe0d"
    FirebaseConfiguration.connect()
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
    UserRoute(this)
}

suspend fun PipelineContext<Unit, ApplicationCall>.authenticate(
    call: ApplicationCall,
    block: suspend (User) -> Unit
) {
    UserController().run {
        validate(call.request.parseAuthorizationHeader())
    }?.let {
        block(it)
    } ?: call.respond(HttpURLConnection.HTTP_UNAUTHORIZED)
}