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
    var regA = 0
    var regB = 0
    var regC = 0
    var instruction = 0
    var programAsString = input[4].split(": ")[1]
    var program = programAsString.split(",").map { it.toInt() }
    var output = ""
    val ops = mapOf(
        Pair(0, ::adv),
        Pair(1, ::bxl),
        Pair(2, ::bst),
        Pair(3, ::jnz),
        Pair(4, ::bxc),
        Pair(5, ::out),
        Pair(6, ::bdv),
        Pair(7, ::cdv)
    )
    fun part1(): String {
        init(input[0].split(": ")[1].toInt(), input[1].split(": ")[1].toInt(), input[2].split(": ")[1].toInt())
        while (instruction < program.size) {
            ops[program[instruction]]?.let { it(program[instruction + 1]) }
            instruction += 2
        }
        return output
    }
    fun part2(): Int {
        var a = -1
        var closest = ""
        while (output != programAsString) {
            a++
            init(a, 0, 0)
            while (instruction < program.size && programAsString.startsWith(output)) {
                ops[program[instruction.toInt()]]?.let { it(program[instruction.toInt() + 1].toInt()) }
                instruction += 2
            }
            if (output.length > closest.length) {
                closest = output
            }
        }
        return a
    }
    fun adv(cmb: Int) {
        regA /= 2.0.pow(combo(cmb).toDouble()).toInt()
    }
    fun bxl(lit: Int) {
        regB = regB.xor(lit)
    }
    fun bst(cmb: Int) {
        regB = combo(cmb) % 8
    }
    fun jnz(lit: Int) {
        if (regA != 0) {
            instruction = lit - 2
        }
    }
    fun bxc(unused: Int) {
        regB = regB.xor(regC)
    }
    fun out(cmb: Int) {
        if (output.isNotEmpty()) {
            output += ","
        }
        output += (combo(cmb) % 8).toString()
    }
    fun bdv(cmb: Int) {
        regB = regA / 2.0.pow(combo(cmb).toDouble()).toInt()
    }
    fun cdv(cmb: Int) {
        regC = regA / 2.0.pow(combo(cmb).toDouble()).toInt()
    }
    fun combo(combo: Int): Int {
        return when (combo) {
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 3
            4 -> regA
            5 -> regB
            6 -> regC
            else -> error("invalid combo operator")
        }
    }
    fun init(a: Int, b: Int, c: Int) {
        regA = a
        regB = b
        regC = c
        instruction = 0
        output = ""
    }
}