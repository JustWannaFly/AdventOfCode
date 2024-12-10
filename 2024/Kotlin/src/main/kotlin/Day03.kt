package com.github.justwannafly

fun main() {
    println("---${Constants.INTRO} Day 03---")
    val input: List<String> = Constants.readResource("03")
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
    fun part2() : String {
        return ""
    }
}