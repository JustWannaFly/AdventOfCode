package com.github.justwannafly

import java.io.File

class Utilities {
    companion object {
        const val INTRO = "AdventOfCode 2024"
        fun readResource(resource: String): List<String> {
            return File("2024/Kotlin/src/main/resources/$resource").readLines()
        }
    }


}
