package game

import com.theorangeteam.JsonLoaderForTests
import com.theorangeteam.contract.Parser
import com.theorangeteam.game.Game
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameParserTest {

    private fun Parser.parseGamesFromJson(json: String): Array<Game>? =
        parseArrayFromJson(json)

    lateinit var gameParserUnderTest: Parser

    @Before
    fun setUp() {
        gameParserUnderTest = Parser()
    }

    @Test
    fun shouldReturnListWithCorrectNames() {
        val expectedArray = listOf(
            Game(name = "Illusion of Gaia", cover = 62764),
            Game(name = "Quidditch Manager", cover = 40564),
            Game(name = "Whitevale Defender", cover = 0)
        ).toTypedArray()
        val receivedJson = JsonLoaderForTests().load("gameList.json")
        assertArrayEquals(expectedArray, gameParserUnderTest.parseGamesFromJson(receivedJson))
    }
}