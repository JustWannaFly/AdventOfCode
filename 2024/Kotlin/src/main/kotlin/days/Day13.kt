package com.github.justwannafly.days

import com.github.justwannafly.Utilities

fun main() {
    println("---${Utilities.INTRO} Day 13---")
    val input: List<String> = Utilities.readResource("13")
    val day13 = Day13(input)
    println("Part 1: ${day13.part1()}")
    println("Part 2: ${day13.part2()}")
}
class Day13(data: List<String>) {
    val machines = parse(data)
    fun part1(): Int {
        return machines.count()
    }
    fun part2(): Int {
        return 0
    }
    fun parse(data: List<String>): List<Machine> {
        val machines = mutableListOf<Machine>()
        var ax = 0
        var ay = 0
        var bx = 0
        var by = 0
        var px = 0
        var py = 0
        data.forEach {
            if (it.startsWith("Button A:")) {
                val xy = getSteps(it.split(": ")[1])
                ax = xy.first
                ay = xy.second
            }
            else if (it.startsWith("Button B:")) {
                val xy = getSteps(it.split(": ")[1])
                bx = xy.first
                by = xy.second
            }
            else if (it.startsWith("Prize:")) {
                val parts = it.split(": ")[1].split(", ")
                px = parts[0].split('=')[1].toInt()
                py = parts[1].split('=')[1].toInt()
            } else {
                machines.add(Machine(ax, ay, bx, by, px, py))
            }
        }
        machines.add(Machine(ax, ay, bx, by, px, py))
        return machines
    }
    private fun getSteps(input: String): Pair<Int, Int> {
        val parts = input.split(", ")
        return Pair(parts[0].split('+')[1].toInt(), parts[1].split('+')[1].toInt())
    }
    class Machine(
        val ax: Int,
        val ay: Int,
        val bx: Int,
        val by: Int,
        val px: Int,
        val py: Int)
}