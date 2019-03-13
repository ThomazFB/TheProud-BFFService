package com.theorangeteam.game

import com.theorangeteam.JsonLoaderForTests
import com.theorangeteam.translate.Parser
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameParserTest {

    private fun Parser.parseGameListFromJson(json: String): Array<Game>? =
        parseArrayFromJson(json)

    private fun Parser.parseGameFromJson(json: String): Game? =
        parseBFFModelFromJson(json)

    lateinit var gameParserUnderTest: Parser

    @Before
    fun setUp() {
        gameParserUnderTest = Parser()
    }

    @Test
    fun shouldReturnGameListWithCorrectInfo() {
        val expectedArray = listOf(
            Game(name = "Illusion of Gaia", cover = 62764),
            Game(name = "Quidditch Manager", cover = 40564),
            Game(name = "Whitevale Defender", cover = 0)
        ).toTypedArray()
        val receivedJson = JsonLoaderForTests.load("gameList.json")
        assertArrayEquals(expectedArray, gameParserUnderTest.parseGameListFromJson(receivedJson))
    }

    @Test
    fun shouldReturnGameWithCorrectInfo() {
        val expectedGame = Game(name = "Illusion of Gaia", cover = 62764)
        val receivedJson = JsonLoaderForTests.load("game.json")
        assertEquals(expectedGame, gameParserUnderTest.parseGameFromJson(receivedJson))
    }

}