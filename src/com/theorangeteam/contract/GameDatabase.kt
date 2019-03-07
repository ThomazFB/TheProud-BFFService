package contract

import game.IGDBDatabase

interface GameDatabase {

    companion object {
        fun default(): GameDatabase = IGDBDatabase()
    }

    fun requestGameJsonListFromQuery(query: String): String
}