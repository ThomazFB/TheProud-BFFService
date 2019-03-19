package com.theorangeteam.api.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord

class FirebaseUserAPI {

    private val auth get() = FirebaseAuth.getInstance()


    fun loadUser(firebaseUserID: String): UserRecord {
        return auth.getUser(firebaseUserID)
    }

    fun loaderUserFromToken(firebaseToken: String): UserRecord {
        return loadUser(auth.verifyIdToken(firebaseToken).uid)
    }

    fun loadToken(firebaseUserID: String): String {
        return auth.createCustomToken(firebaseUserID)
    }

    fun createUser(email: String, password: String, username: String): UserRecord {
        val request = UserRecord.CreateRequest().apply {
            setEmail(email)
            setEmailVerified(false)
            setPassword(password)
            setDisplayName(username)
        }

        return auth.createUser(request)
    }
}