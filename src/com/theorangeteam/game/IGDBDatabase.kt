package com.theorangeteam.game

import Endpoint
import IGDBWrapper.apiJsonRequest
import com.theorangeteam.contract.GameDatabase

class IGDBDatabase : GameDatabase {

    override fun requestGameJsonListFromQuery(query: String): String {
        try {
            return apiJsonRequest(Endpoint.GAMES, query)
        } catch (ex: Exception) {
            return ex.localizedMessage
        }
    }
}