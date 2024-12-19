package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import kotlin.math.pow

fun main() {
    println("---${Utilities.INTRO} Day 17---")
    val input: List<String> = Utilities.readResource("17")
    val day17 = Day17(input)
    println("Part 1: ${day17.part1()}")
    println("Part 2: ${day17.part2()}")
}

class Day17(private val input: List<String>) {
    var regA = 0L
    var regB = 0L
    var regC = 0L
    var instruction = 0
    var programAsString = input[4].split(": ")[1]
    var program = programAsString.split(",").map { it.toLong() }
    var output = ""
    val ops = mapOf(
        Pair(0L, ::adv),
        Pair(1L, ::bxl),
        Pair(2L, ::bst),
        Pair(3L, ::jnz),
        Pair(4L, ::bxc),
        Pair(5L, ::out),
        Pair(6L, ::bdv),
        Pair(7L, ::cdv)
    )
    fun part1(): String {
        init(input[0].split(": ")[1].toLong(), input[1].split(": ")[1].toLong(), input[2].split(": ")[1].toLong())
        while (instruction < program.size) {
            ops[program[instruction]]?.let { it(program[instruction + 1]) }
            instruction += 2
        }
        return output
    }
    fun part2(): Long {
        var aSet = mutableSetOf(0L)
        var builtOutput = ""
        program.reversed().forEach { outputChar ->
            if (builtOutput.isNotEmpty()) {
                builtOutput = "$outputChar,$builtOutput"
            } else {
                builtOutput = "$outputChar"
            }
            val workingASet = mutableSetOf<Long>()
            aSet.forEach {
                var i = 0
                while (i <= 7) {
                    val workingA = it.shl(3) + i
                    init(workingA, 0L, 0L)
                    while (instruction < program.size && output.length <= builtOutput.length) {
                        ops[program[instruction]]?.let { it(program[instruction + 1]) }
                        instruction += 2
                    }
                    if (output == builtOutput) {
                        workingASet.add(workingA)
                    }
                    i++
                }
            }
            aSet = workingASet
        }
        init(aSet.min(), 0L, 0L)
        while (instruction < program.size) {
            ops[program[instruction]]?.let { it(program[instruction + 1]) }
            instruction += 2
        }
        println(output)
        println(programAsString)
        return aSet.min()
    }
    fun adv(cmb: Long) {
        regA = regA.shr(cmb.toInt())
    }
    fun bxl(lit: Long) {
        regB = regB.xor(lit)
    }
    fun bst(cmb: Long) {
        regB = combo(cmb) % 8
    }
    fun jnz(lit: Long) {
        if (regA != 0L) {
            instruction = lit.toInt() - 2
        }
    }
    fun bxc(unused: Long) {
        regB = regB.xor(regC)
    }
    fun out(cmb: Long) {
        if (output.isNotEmpty()) {
            output += ","
        }
        output += (combo(cmb) % 8).toString()
    }
    fun bdv(cmb: Long) {
        regB = regA.shr(cmb.toInt())
    }
    fun cdv(cmb: Long) {
        regC = regA.shr(cmb.toInt())
    }
    fun combo(combo: Long): Long {
        return when (combo) {
            0L -> 0
            1L -> 1
            2L -> 2
            3L -> 3
            4L -> regA
            5L -> regB
            6L -> regC
            else -> error("invalid combo operator")
        }
    }
    fun init(a: Long, b: Long, c: Long) {
        regA = a
        regB = b
        regC = c
        instruction = 0
        output = ""
    }
}