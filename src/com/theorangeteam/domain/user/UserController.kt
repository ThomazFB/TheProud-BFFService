package com.theorangeteam.domain.user

import com.theorangeteam.api.firebase.FirebaseUserAPI
import io.ktor.application.call
import io.ktor.auth.AuthenticationPipeline
import io.ktor.auth.UnauthorizedResponse
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.parseAuthorizationHeader
import io.ktor.http.auth.HttpAuthHeader
import io.ktor.response.respond

class UserController(private val userAPI: FirebaseUserAPI = FirebaseUserAPI()) {

    fun login(userID: String): User? {
        return try {
            val token = userAPI.loadToken(userID)
            val record = userAPI.loadUser(userID)
            return User.fromRecordWithToken(record, token)
        } catch (ex: Exception) {
            null
        }
    }

    fun validate(token: String): User? {
        return try {
            return User.fromRecordWithToken(userAPI.loaderUserFromToken(token), token)
        } catch (ex: Exception) {
            null
        }
    }

    private fun userFromHeader(authHeader: HttpAuthHeader?): User? {
        return takeUnless { authHeader == null || authHeader.authScheme != "Bearer" }?.let {
            validate(authHeader!!.render())
        }
    }

    fun AuthenticationPipeline.bearerAuthentication(realm: String) {
        intercept(AuthenticationPipeline.RequestAuthentication) { context ->
            val authHeader = call.request.parseAuthorizationHeader()
            userFromHeader(authHeader)?.let {
                context.principal(UserIdPrincipal(name = it.name))
            } ?: call.respond(UnauthorizedResponse())
        }
    }
}