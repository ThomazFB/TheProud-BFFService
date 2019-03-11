package com.theorangeteam
import java.io.File

object JsonLoaderForTests {
    val rootPath = "test/com/theorangeteam/resources/"

    fun load(path: String): String {
        return File("$rootPath$path").readText()
    }
}