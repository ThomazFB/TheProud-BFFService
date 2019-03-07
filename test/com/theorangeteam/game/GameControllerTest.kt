package game

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.theorangeteam.JsonLoaderForTests
import com.theorangeteam.contract.Parser
import com.theorangeteam.game.Game
import contract.GameDatabase
import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.validateMockitoUsage



class GameControllerTest {
    private val requestedJson
        get() = JsonLoaderForTests().load("gameList.json")

    lateinit var gameControllerUnderTest: GameController

    @Mock
    lateinit var parserMock: Parser

    @Mock
    lateinit var gameDBMock: GameDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gameControllerUnderTest = GameController(parserMock, gameDBMock)
        whenever(gameDBMock.requestGameJsonListFromQuery(anyString())) doReturn requestedJson
    }

    @Test
    fun shouldLoadGameListFromDatabaseWhenGameListIsCalled() {
        val gameArray = gameControllerUnderTest.loadGames()
        assertArrayEquals(parserMock.parseArrayFromJson<Game>(requestedJson), gameArray)
    }

    @Test
    fun shouldCallDatabaseWhenGameListIsCalled() {
        gameControllerUnderTest.loadGames()
        verify(gameDBMock).requestGameJsonListFromQuery(anyString())
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}