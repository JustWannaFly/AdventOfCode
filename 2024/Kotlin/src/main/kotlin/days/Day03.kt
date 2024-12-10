package com.github.justwannafly.days

import com.github.justwannafly.Utilities

fun main() {
    println("---${Utilities.INTRO} Day 03---")
    val input: List<String> = Utilities.readResource("03")
        ?: error("unable to read input")
    val day3 = Day03(input)
    println("Part 1: ${day3.part1()}")
    println("Part 2: ${day3.part2()}")
}

class Day03(
    private val input: List<String>,
) {

    companion object {
        const val DIGIT_ARGS = "\\d{1,3},\\d{1,3}"
        const val MUL_OPERATION = "mul\\($DIGIT_ARGS\\)"
        const val DO_OPERATION = "do\\(\\)"
        const val DONT_OPERATION = "don't\\(\\)"
        const val MUL_DO_DONT = "$MUL_OPERATION|$DO_OPERATION|$DONT_OPERATION"
    }



    fun part1() : Int {
        var sum = 0
        val mulRegex = Regex(MUL_OPERATION)
        val digitRegex = Regex(DIGIT_ARGS)
        input.forEach { line ->
            mulRegex.findAll(line).forEach { mulMatch ->
                val digits = digitRegex.find(mulMatch.value)?.value?.split(',')?.map { it.toInt() }
                if (digits != null && digits.size == 2) {
                    sum += digits[0] * digits[1]
                }
            }
        }
        return sum
    }
    fun part2() : Int {
        var sum = 0
        val mulDoDontRegex = Regex(MUL_DO_DONT)
        val digitRegex = Regex(DIGIT_ARGS)
        val doRegex = Regex(DO_OPERATION)
        val dontRegex = Regex(DONT_OPERATION)
        var enabled = true
        input.forEach { line ->
            mulDoDontRegex.findAll(line).forEach { mulDoDont ->
                if (doRegex.matches(mulDoDont.value)) {
                    enabled = true
                } else if (dontRegex.matches(mulDoDont.value)){
                    enabled = false
                } else if (enabled) {
                    val digits = digitRegex.find(mulDoDont.value)?.value?.split(',')?.map { it.toInt() }
                    if (digits != null && digits.size == 2) {
                        sum += digits[0] * digits[1]
                    }
                }
            }
        }
        return sum
    }
}