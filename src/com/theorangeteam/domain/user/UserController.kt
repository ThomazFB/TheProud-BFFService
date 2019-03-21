package com.theorangeteam.domain.user

import com.auth0.jwt.JWT
import com.theorangeteam.api.firebase.FirebaseUserAPI
import com.theorangeteam.domain.auth.Token
import io.ktor.http.auth.HttpAuthHeader

class UserController(private val userAPI: FirebaseUserAPI = FirebaseUserAPI()) {

    fun createUserToken(userID: String): Token? {
        return try {
            Token(userAPI.loadToken(userID))
        } catch (ex: Exception) {
            null
        }
    }

    private fun validateUserFromToken(token: String): User? {
        return try {
            val uid = JWT.decode(token).getClaim("uid")
            return User.fromRecordWithToken(userAPI.loadUser(uid.asString()))
        } catch (ex: Exception) {
            null
        }
    }

    fun validate(authHeader: HttpAuthHeader?): User? {
        return takeUnless { authHeader == null || authHeader.authScheme != "Bearer" || authHeader !is HttpAuthHeader.Single }?.let {
            val authHeaderSingle = authHeader as HttpAuthHeader.Single
            validateUserFromToken(authHeaderSingle.blob)
        }
    }
}