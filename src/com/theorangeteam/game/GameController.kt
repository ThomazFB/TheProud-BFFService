package game

import com.theorangeteam.contract.Parser
import com.theorangeteam.game.Game
import contract.GameDatabase

class GameController(private val parser: Parser = Parser(),
                    private val gameDB: GameDatabase = GameDatabase.default()) {

    fun loadGames(): Array<Game>? =
        parser.parseArrayFromJson(gameDB.requestGameJsonListFromQuery("fields *;"))
}