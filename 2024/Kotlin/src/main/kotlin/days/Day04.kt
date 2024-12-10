package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.datastructures.TwoDMap

fun main() {
    println("---${Utilities.INTRO} Day 04---")
    val input: List<String> = Utilities.readResource("04")
    val day4 = Day04(input)
    println("Part 1: ${day4.part1()}")
    println("Part 2: ${day4.part2()}")
}

class Day04(input: List<String>,
) {
    private val data: TwoDMap<Char> = TwoDMap(input.map { it.toCharArray().toList() })

    companion object {
        val searchWord = "XMAS"
    }

    fun part1() : Int {
        var count = 0
        var y = 0
        while (y < data.getHeight()) {
            var x = 0
            while (x < data.getWidth()) {
                if (data.get(x, y) == searchWord[0]) {
                    TwoDMap.Direction.entries.forEach {
                        if (isMatch(searchWord, x, y, it)) {
                            count++
                        }
                    }
                }
                x++
            }
            y++
        }
        return count
    }
    fun part2() : Int {
        return 0
    }

    fun isMatch(word: String, x: Int, y: Int, dir: TwoDMap.Direction): Boolean {
        var step = 0
        word.toCharArray().forEach {
            if (it != data.getNeighbor(x, y, dir, step)) {
                return false
            }
            step++
        }
        return true
    }
}