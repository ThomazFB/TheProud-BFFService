package com.theorangeteam.igdb

import com.theorangeteam.contract.GameRepository
import com.theorangeteam.igdb.IGDBConfiguration.Route.*
import com.theorangeteam.igdb.IGDBConfiguration.Field.*
import com.theorangeteam.igdb.IGDBConfiguration.endpoint
import com.theorangeteam.igdb.IGDBConfiguration.loadGameRequestBody
import com.theorangeteam.igdb.IGDBConfiguration.setupHeader
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.coroutines.runBlocking

class IGDBRepository : GameRepository {

    private val httpClient by lazy { HttpClient(Apache) }

    override fun requestGameJsonListFromQuery(query: String): String {
        return runBlocking {
            httpClient.post<String>("$endpoint${GameList.value}", block = {
                headers { setupHeader(this) }
                parameter("fields", "${GameName.value},${GameCover.value}")
            })
        }
    }

    override fun requestSingleGame(gameID: Int): String {
        return runBlocking {
            httpClient.post<String>("$endpoint${GameList.value}", block = {
                headers { setupHeader(this) }
                body = loadGameRequestBody(gameID)
            })
        }
    }
}