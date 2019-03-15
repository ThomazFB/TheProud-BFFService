package com.theorangeteam.domain.user

import com.theorangeteam.api.firebase.FirebaseUserAPI

class UserController(private val userAPI: FirebaseUserAPI = FirebaseUserAPI()) {

    fun login(userID: String): User? {
        return User.fromRecord(userAPI.loadUser(userID))
    }

    fun generateToken(userID: String): String? {
        return try {
            userAPI.loadToken(userID)
        } catch (ex: Exception) {
            null
        }
    }
}