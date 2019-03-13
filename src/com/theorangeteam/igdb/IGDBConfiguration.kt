package com.theorangeteam.igdb

import io.ktor.http.HeadersBuilder

object IGDBConfiguration {

    const val endpoint = "https://api-v3.igdb.com"

    private val gameFields = "fields " +
            "${Field.GameName.value},${Field.GameCover.value};"

    fun setupHeader(builder: HeadersBuilder) =
            builder.apply {
                set("user-key", IGDBWrapper.userkey)
                set("Accept", "application/json")
            }

    fun loadGameRequestBody(gameID: Int): String {
        return "$gameFields where id = $gameID;"
    }

    enum class Field(val value: String) {
        GameName("name"),
        GameCover("cover")
    }

    enum class Route(val value: String) {
        GameList("/games")
    }
}