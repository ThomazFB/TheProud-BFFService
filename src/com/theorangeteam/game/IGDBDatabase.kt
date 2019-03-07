package game

import IGDBWrapper.apiJsonRequest
import contract.GameDatabase

class IGDBDatabase: GameDatabase {

    override fun requestGameJsonListFromQuery(query: String): String {
        return apiJsonRequest(Endpoint.GAMES, query)
    }
}