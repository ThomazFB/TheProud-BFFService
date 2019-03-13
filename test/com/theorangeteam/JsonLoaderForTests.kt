package com.theorangeteam
import java.io.File

object JsonLoaderForTests {
    const val rootPath = "test/com/theorangeteam/resources/"

    fun load(path: String): String {
        return File("$rootPath$path").readText()
    }

    fun loadWithRoot(rootedPath: String): String {
        return File(rootedPath).readText()
    }
}