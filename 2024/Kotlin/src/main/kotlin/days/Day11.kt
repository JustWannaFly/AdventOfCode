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

    fun part1(): Long {
        val rocks = input[0].split(' ').map { it.toLong() }
        return stepNTimesCount(rocks, 25)
    }

    fun part2(): Long {
        val rocks = input[0].split(' ').map { it.toLong() }
        return stepNTimesCount(rocks, 75)
    }
    fun stepNTimesCount(initial: List<Long>, steps: Long): Long {
        var numCounts = mutableMapOf<Long, Long>()
        initial.forEach {
            if (numCounts.containsKey(it)) {
                numCounts[it] = numCounts[it]!! + 1
            } else {
                numCounts[it] = 1
            }
        }
        var i = 0
        while (i < steps) {
            val mulStepped = numCounts.keys.map { Pair(stepNum(it), numCounts[it]!!) }
            numCounts = mutableMapOf()
            mulStepped.forEach { listMulPair ->
                listMulPair.first.forEach {
                    if (numCounts.containsKey(it)) {
                        numCounts[it] = numCounts[it]!! + listMulPair.second
                    } else {
                        numCounts[it] = listMulPair.second
                    }
                }
            }
            if (numCounts.values.sum()< 0) {
                println("whoops we went negative. make it a long")
            }
            i++
        }
        return numCounts.values.sum()
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