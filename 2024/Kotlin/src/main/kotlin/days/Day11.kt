package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import kotlin.math.pow

fun main() {
    println("---${Utilities.INTRO} Day 11---")
    val input: List<String> = Utilities.readResource("11")
    val day5 = Day11(input)
    println("Part 1: ${day5.part1()}")
    println("Part 2: ${day5.part2()}")
}

class Day11(private val input: List<String>) {

    fun part1(): Int {
        val rocks = input[0].split(' ').map { it.toLong() }
        return stepNTimesCount(rocks, 25)
    }

    fun part2(): Int {
        val rocks = input[0].split(' ').map { it.toLong() }
        return stepNTimesCount(rocks, 75)
    }
    fun stepNTimesCount(initial: List<Long>, steps: Int): Int {
        var rocks = initial
        var i = 0
        while (i < steps) {
            val steppedRocks = mutableListOf<Long>()
            rocks.forEach{ steppedRocks.addAll(stepNum(it)) }
            rocks = steppedRocks
            i++
        }
        return rocks.size
    }
    fun stepNum(num: Long): List<Long> {
        if (num == 0L) return listOf(1)
        if (num.toString().length % 2 == 0) {
            val ten: Double = 10.0
            val multiplier = ten.pow(num.toString().length / 2).toLong()
            return listOf(num / multiplier, num % multiplier)
        }
        return listOf(num * 2024)
    }
}