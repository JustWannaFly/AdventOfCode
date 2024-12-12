package com.github.justwannafly.days

import com.github.justwannafly.Utilities


fun main() {
    println("---${Utilities.INTRO} Day 07---")
    val input: List<String> = Utilities.readResource("07")
    val day5 = Day07(input)
    println("Part 1: ${day5.part1()}")
    println("Part 2: ${day5.part2()}")
}

class Day07(private val data: List<String>) {
    fun part1(): Long {
        return data.filter { canRectify(it, listOf('+', '*')) }.sumOf { it.split(':')[0].toLong() }
    }
    fun part2(): Long {
        return data.filter { canRectify(it, listOf('+', '*', '|')) }.sumOf { it.split(':')[0].toLong() }
    }
    fun canRectify(line: String, ops: List<Char>): Boolean {
        val nums = getNums(line)
        val target = nums.removeFirst()
        var working = -1L
        buildPermutations(ops, nums.size - 1).forEach { permutation: List<Char> ->
            working = nums[0]
            var i = 1
            while (i < nums.size) {
                working = doOp(working, nums[i], permutation[i - 1])
                i++
            }
            if (target == working) {
                return true
            }
        }
        return false
    }
    fun getNums(line: String): MutableList<Long> {
        return line.replace(":", "").split(' ').map { it.toLong() }.toMutableList()
    }
    fun doOp(num1: Long, num2: Long, op: Char): Long {
        return when (op) {
            '+' -> num1 + num2
            '*' -> num1 * num2
            '|' -> (num1.toString() + num2.toString()).toLong()
            else -> 0L
        }
    }
    fun buildPermutations(ops: List<Char>, numOfOps: Int): List<List<Char>> {
        if (numOfOps <= 1) {
            return ops.map{listOf(it)}
        }
        val combinedLists = mutableListOf<List<Char>>()
        val tails = buildPermutations(ops, numOfOps - 1)
        ops.forEach { op ->
            tails.forEach { tail ->
                val workingList = mutableListOf(op)
                workingList.addAll(tail)
                combinedLists.add(workingList)
            }
        }
        return combinedLists
    }
}