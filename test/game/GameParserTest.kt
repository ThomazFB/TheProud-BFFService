package game

import com.theorangeteam.TestJsonLoader
import com.theorangeteam.contract.Parser
import com.theorangeteam.game.Game
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameParserTest {

    class GameParser: Parser() {

        fun parseGamesFromJson(json: String): Array<Game>? =
            parseArrayFromJson(json)
    }

    lateinit var gameParserUnderTest: GameParser
    val jsonLoader = TestJsonLoader()

    @Before
    fun setUp() {
        gameParserUnderTest = GameParser()
    }

    @Test
    fun shouldReturnBuildedList() {
        val expectedArray = listOf(
            Game(title = "1"),
            Game(title = "2"),
            Game(title = "3")
        ).toTypedArray()
        val receivedJson = jsonLoader.load("gameList.json")
        assertArrayEquals(expectedArray, gameParserUnderTest.parseGamesFromJson(receivedJson))
    }
}