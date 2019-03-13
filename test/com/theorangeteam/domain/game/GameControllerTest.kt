package com.theorangeteam.domain.game

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.theorangeteam.JsonLoaderForTests
import com.theorangeteam.contract.JsonAPI
import com.theorangeteam.domain.translate.Parser
import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.validateMockitoUsage
import org.mockito.MockitoAnnotations


class GameControllerTest {
    private val requestedJson
        get() = JsonLoaderForTests.load("gameList.json")

    lateinit var gameControllerUnderTest: GameController

    @Mock
    lateinit var parserMock: Parser

    @Mock
    lateinit var DBMock: JsonAPI

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gameControllerUnderTest = GameController(parserMock, DBMock)
        whenever(DBMock.requestJsonListFromQuery(anyString())) doReturn requestedJson
    }

    @Test
    fun shouldLoadGameListFromDatabaseWhenGameListIsCalled() {
        val gameArray = gameControllerUnderTest.loadGames()
        assertArrayEquals(parserMock.parseArrayFromJson<Game>(requestedJson), gameArray)
    }

    @Test
    fun shouldCallDatabaseWhenGameListIsCalled() {
        gameControllerUnderTest.loadGames()
        verify(DBMock).requestJsonListFromQuery(anyString())
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}