package com.theorangeteam.domain.user

import com.google.firebase.auth.UserRecord

class User(val name: String = "Default", val email: String) {

    companion object {
        fun fromRecordWithToken(firebaseUserRecord: UserRecord): User =
            User(email = firebaseUserRecord.email)
    }
}