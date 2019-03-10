package com.theorangeteam.game

import IGDBWrapper
import com.theorangeteam.contract.GameDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.coroutines.runBlocking

class IGDBDatabase : GameDatabase {

    val httpClient by lazy { HttpClient(Apache) }

    override fun requestGameJsonListFromQuery(query: String): String {
        return runBlocking {
            httpClient.post<String>("https://api-v3.igdb.com/games", block = {
                headers {
                    set("user-key", IGDBWrapper.userkey)
                    set("Accept", "application/json")
                }
                parameter("fields", "name,cover")
            })
        }
    }
}