package com.theorangeteam.api.igdb

import com.theorangeteam.contract.JsonAPI
import com.theorangeteam.api.igdb.IGDBConfiguration.Route.*
import com.theorangeteam.api.igdb.IGDBConfiguration.Field.*
import com.theorangeteam.api.igdb.IGDBConfiguration.endpoint
import com.theorangeteam.api.igdb.IGDBConfiguration.loadGameRequestBody
import com.theorangeteam.api.igdb.IGDBConfiguration.setupHeader
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.coroutines.runBlocking

class IGDBGameAPI : JsonAPI {

    private val httpClient by lazy { HttpClient(Apache) }

    override fun requestJsonListFromQuery(query: String): String {
        return runBlocking {
            httpClient.post<String>("$endpoint${GameList.value}", block = {
                headers { setupHeader(this) }
                parameter("fields", "${GameName.value},${GameCover.value}")
            })
        }
    }

    override fun requestSingleObject(objectID: Int): String {
        return runBlocking {
            httpClient.post<String>("$endpoint${GameList.value}", block = {
                headers { setupHeader(this) }
                body = loadGameRequestBody(objectID)
            })
        }
    }
}