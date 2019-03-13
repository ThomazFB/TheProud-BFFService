package com.theorangeteam.domain.user

import com.google.firebase.auth.UserRecord

class User(val name: String?, val email: String) {

    companion object {
        fun fromRecord(firebaseUserRecord: UserRecord): User {
            return User(firebaseUserRecord.displayName, firebaseUserRecord.email)
        }
    }
}