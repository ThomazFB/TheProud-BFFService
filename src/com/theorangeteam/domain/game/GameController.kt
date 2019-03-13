package com.theorangeteam.domain.game

import com.theorangeteam.api.igdb.IGDBGameAPI
import com.theorangeteam.contract.JsonAPI
import com.theorangeteam.domain.translate.Parser

class GameController(
    private val parser: Parser = Parser(),
    private val gameAPI: JsonAPI = IGDBGameAPI()
) {

    fun loadGames(): Array<Game>? =
        parser.parseArrayFromJson(gameAPI.requestJsonListFromQuery("fields *;"))


    fun loadSingleGame(gameID: Int): Game? {
        val gameArray = parser.parseBFFModelFromJson<Array<Game>>(gameAPI.requestSingleObject(gameID))
        gameArray?.takeIf { it.isNotEmpty() }?.let {
            return it.first()
        } ?: return null
    }

}