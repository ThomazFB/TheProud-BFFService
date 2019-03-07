package com.theorangeteam
import java.io.File

class TestJsonLoader {
    val rootPath = "test/resources/"

    fun load(path: String): String {
        return File("$rootPath$path").readText()
    }
}