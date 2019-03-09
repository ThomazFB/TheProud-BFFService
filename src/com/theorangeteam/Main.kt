package com.theorangeteam

import io.ktor.server.jetty.EngineMain

object Main{
    @Suppress("unused") // Referenced in application.conf
    @JvmStatic
    fun main(args: Array<String>) = EngineMain.main(args)
}