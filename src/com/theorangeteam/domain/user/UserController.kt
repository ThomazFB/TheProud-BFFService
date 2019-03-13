package com.theorangeteam.domain.user

import com.theorangeteam.api.firebase.FirebaseUserAPI

class UserController(private val userAPI: FirebaseUserAPI = FirebaseUserAPI()) {

    fun loadUser(userID: String): User? {
        return User.fromRecord(userAPI.loadUser(userID))
    }
}