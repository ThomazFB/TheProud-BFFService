package com.theorangeteam.game

import com.theorangeteam.contract.GameRepository
import com.theorangeteam.contract.Parser

class GameController(
    private val parser: Parser = Parser(),
    private val gameDB: GameRepository = GameRepository.default()
) {

    fun loadGames(): Array<Game>? =
        parser.parseArrayFromJson(gameDB.requestGameJsonListFromQuery("fields *;"))


    fun loadSingleGame(gameID: Int): Game? {
        val gameArray = parser.parseBFFModelFromJson<Array<Game>>(gameDB.requestSingleGame(gameID))
        gameArray?.takeIf { it.isNotEmpty() }?.let {
            return it.first()
        } ?: return null
    }

}