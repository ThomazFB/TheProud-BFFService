package com.theorangeteam.domain.user

import com.google.firebase.auth.UserRecord

class User(val name: String?, val email: String, val token: String = "") {

    companion object {
        fun fromRecordWithToken(firebaseUserRecord: UserRecord, token: String): User =
            User(firebaseUserRecord.displayName, firebaseUserRecord.email, token)
    }
}