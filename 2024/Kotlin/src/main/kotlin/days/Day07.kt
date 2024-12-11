package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import kotlin.math.pow


fun main() {
    println("---${Utilities.INTRO} Day 07---")
    val input: List<String> = Utilities.readResource("07")
    val day5 = Day07(input)
    println("Part 1: ${day5.part1()}")
    println("Part 2: ${day5.part2()}")
}

class Day07(private val data: List<String>) {
    fun part1(): Long {
        return data.filter { canRectify(it) }.sumOf { it.split(':')[0].toLong() }
    }
    fun part2(): Long {
        return 0
    }
    fun canRectify(line: String): Boolean {
        val nums = getNums(line)
        val target = nums.removeFirst()
        var op = 0
        var working = -1L
        while (op < 2.0.pow(nums.size - 1) && target != working) {
            working = nums[0]
            var i = 1
            while (i < nums.size) {
                working = if ((op and 2.0.pow(i-1).toInt()) == 0) working + nums[i] else working * nums[i]
                i++
            }
            op++
        }
        return target == working
    }
    fun getNums(line: String): MutableList<Long> {
        return line.replace(":", "").split(' ').map { it.toLong() }.toMutableList()
    }
}