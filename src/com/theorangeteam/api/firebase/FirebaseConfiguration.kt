package com.theorangeteam.api.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

object FirebaseConfiguration {
    private val firebaseUrl get() = "https://the-proud.firebaseio.com"
    private val serviceAccount get() = FileInputStream("resources/the-proud-firebase-adminsdk.json")

    fun connect() {
        val options = FirebaseOptions.builder().apply {
            setCredentials(GoogleCredentials.fromStream(serviceAccount))
            setDatabaseUrl(firebaseUrl)
        }.build()

        FirebaseApp.initializeApp(options)
    }
}