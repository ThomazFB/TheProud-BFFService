package com.theorangeteam.game

import com.theorangeteam.contract.GameRepository
import com.theorangeteam.translate.Parser

class GameController(
    private val parser: Parser = Parser(),
    private val repo: GameRepository = GameRepository.default()
) {

    fun loadGames(): Array<Game>? =
        parser.parseArrayFromJson(repo.requestGameJsonListFromQuery("fields *;"))


    fun loadSingleGame(gameID: Int): Game? {
        val gameArray = parser.parseBFFModelFromJson<Array<Game>>(repo.requestSingleGame(gameID))
        gameArray?.takeIf { it.isNotEmpty() }?.let {
            return it.first()
        } ?: return null
    }

}