package com.github.justwannafly

import java.io.File

class Constants{
    companion object {
        val INTRO = "AdventOfCode 2024"
        fun readResource(resource: String): List<String>? {
            return File("2024/Kotlin/src/main/resources/$resource").readLines()
        }
    }


}
