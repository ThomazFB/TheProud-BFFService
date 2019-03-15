package com.theorangeteam.domain.user

import com.theorangeteam.api.firebase.FirebaseUserAPI

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
}