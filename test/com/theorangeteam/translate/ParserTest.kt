package com.theorangeteam.translate

import com.theorangeteam.contract.Parser
import com.theorangeteam.game.Game
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.EOFException

class ParserTest {

    lateinit var parserUnderTest: Parser

    @Before
    fun setUp() {
        parserUnderTest = Parser()
    }

}