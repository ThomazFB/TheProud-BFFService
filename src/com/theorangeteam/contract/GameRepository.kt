package com.theorangeteam.contract

import com.theorangeteam.igdb.IGDBRepository

interface GameRepository {

    companion object {
        fun default(): GameRepository = IGDBRepository()
    }

    fun requestGameJsonListFromQuery(query: String): String
    fun requestSingleGame(gameID: Int): String
}