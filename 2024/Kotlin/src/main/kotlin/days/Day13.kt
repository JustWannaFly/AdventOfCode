package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import kotlin.math.max
import kotlin.math.min

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
        var cost = 0
        machines.forEach { machine ->
            val aMax = min(min(machine.px/machine.ax, machine.py/machine.ay), 100)
            val bMax = min(min(machine.px/machine.bx, machine.py/machine.by), 100)
            var minCost = (3 * aMax) + bMax
            var aCount = aMax
            var bCount = max(max((machine.px - (aCount * machine.ax))/machine.bx,
                                (machine.py - (aCount * machine.ay))/machine.by), 0)
            var canWin = false
            while (aCount >= 0) {
                while ((machine.ax * aCount) + (machine.bx * bCount) < machine.px
                    && (machine.ay * aCount) + (machine.by * bCount) < machine.py) {
                    bCount++
                }
                if (bCount <= bMax
                    && (machine.ax * aCount) + (machine.bx * bCount) == machine.px
                    && (machine.ay * aCount) + (machine.by * bCount) == machine.py) {
                    minCost = min(minCost, (3 * aCount) + bCount)
                    canWin = true
                }
                aCount--
            }
            if (canWin) {
                cost += minCost
            }
        }
        return cost
    }
    fun part2(): Long {
        val targetOffset = 10000000000000
        var cost = 0L
        machines.forEach { machine ->
            val targetX = machine.px + targetOffset
            val targetY = machine.py + targetOffset
            val aMax = min(targetX/machine.ax, targetY/machine.ay)
            val bMax = min(targetX/machine.bx, targetY/machine.by)
            var minCost = (3 * aMax) + bMax
            var aCount = aMax
            var bCount = max(max((targetX - (aCount * machine.ax))/machine.bx,
                (targetY - (aCount * machine.ay))/machine.by), 0)
            var canWin = false
            while (aCount >= 0) {
                while ((machine.ax * aCount) + (machine.bx * bCount) < targetX
                    && (machine.ay * aCount) + (machine.by * bCount) < targetY) {
                    bCount++
                }
                if (bCount <= bMax
                    && (machine.ax * aCount) + (machine.bx * bCount) == targetX
                    && (machine.ay * aCount) + (machine.by * bCount) == targetY) {
                    minCost = min(minCost, (3 * aCount) + bCount)
                    canWin = true
                }
                aCount--
            }
            if (canWin) {
                cost += minCost
            }
        }
        return cost
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
                val xy = parseSteps(it.split(": ")[1], '+')
                ax = xy.first
                ay = xy.second
            }
            else if (it.startsWith("Button B:")) {
                val xy = parseSteps(it.split(": ")[1], '+')
                bx = xy.first
                by = xy.second
            }
            else if (it.startsWith("Prize:")) {
                val xy = parseSteps(it.split(": ")[1], '=')
                px = xy.first
                py = xy.second
            } else {
                machines.add(Machine(ax, ay, bx, by, px, py))
            }
        }
        machines.add(Machine(ax, ay, bx, by, px, py))
        return machines
    }
    private fun parseSteps(input: String, char: Char): Pair<Int, Int> {
        val parts = input.split(", ")
        return Pair(parts[0].split(char)[1].toInt(), parts[1].split(char)[1].toInt())
    }
    class Machine(
        val ax: Int,
        val ay: Int,
        val bx: Int,
        val by: Int,
        val px: Int,
        val py: Int)
}