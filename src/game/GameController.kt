package com.theorangeteam.game

import com.theorangeteam.contract.Parser

class GameController {
    val parser by lazy { Parser() }

    fun loadGames(): Array<Game>? {
        val json = "[\n" +
                "  {\n" +
                "    \"title\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"2\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"3\"\n" +
                "  }\n" +
                "]\n"
        return parser.parseArrayFromJson(json)
    }
}