package com.theorangeteam.game

import Endpoint
import IGDBWrapper.apiJsonRequest
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.result.Result
import com.theorangeteam.contract.GameDatabase
import java.lang.Exception

class RequestException(val statusCode: Int, val request: Request, val result: Result<String, FuelError>): Exception()

class IGDBDatabase : GameDatabase {

    override fun requestGameJsonListFromQuery(query: String): String {
        try {
            return apiJsonRequest(Endpoint.GAMES, query)
        } catch (ex: RequestException) {
            return "${ex.statusCode}\n${ex.request}\n${ex.result.component1()}\n${ex.result.component2()}"
        }
    }
}