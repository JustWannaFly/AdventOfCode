package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.Utilities.Companion.charToInt

fun main() {
    println("---${Utilities.INTRO} Day 09---")
    val input: List<String> = Utilities.readResource("09")
    val day09 = Day09(input)
    println("Part 1: ${day09.part1()}")
    println("Part 2: ${day09.part2()}")
}

class Day09(private val input: List<String>) {
    fun part1(): Long {
        var isGap = false
        var id = 0
        val empty = -1
        val disk = mutableListOf<Int>()
        input[0].map{ charToInt(it) }.forEach{
            for (i: Int in 1..it) {
                disk.add(if (isGap) empty else id)
            }
            if (!isGap) {
                id++
            }
            isGap = !isGap
        }
        var firstEmpty = disk.indexOfFirst { it == -1 }
        var lastFull = disk.indexOfLast{ it != -1 }
        while (firstEmpty < lastFull && firstEmpty != -1 ) {
            disk[firstEmpty] = disk[lastFull]
            disk.removeAt(lastFull)
            firstEmpty = disk.indexOfFirst { it == -1 }
            lastFull = disk.indexOfLast{ it != -1 }
        }
        disk.removeAll { it == -1 }
        var checksum = 0L
        for (i: Int in 0..<disk.size) {
            checksum += (disk[i] * i).toLong()
        }
        return checksum
    }

    fun part2(): Long {
        var isGap = false
        var id = 0
        val empty = -1
        var disk = mutableListOf<Int>()
        input[0].map{ charToInt(it) }.forEach{
            for (i: Int in 1..it) {
                disk.add(if (isGap) empty else id)
            }
            if (!isGap) {
                id++
            }
            isGap = !isGap
        }
        var currentId = disk[disk.indexOfLast { it != -1 }]
        while (currentId > 0) {
            val fileStart = disk.indexOfFirst { it == currentId }
            val fileEnd = disk.indexOfLast { it == currentId }
            val fileLength = (fileEnd - fileStart) + 1

            var emptyStart = -1
            var emptyLength = 0
            for (i: Int in 0..<fileStart) {
                if (disk[i] != empty) {
                    emptyStart = -1
                    emptyLength = 0
                } else {
                    if (emptyStart == -1) {
                        emptyStart = i
                    }
                    emptyLength++
                    if (emptyLength >= fileLength) {
                        disk = disk.map { if (it == currentId) empty else it }.toMutableList()
                        for (j: Int in emptyStart until emptyStart + fileLength) {
                            disk[j] = currentId
                        }
                        break
                    }
                }
            }
            currentId--
        }
        var checksum = 0L
        for (i: Int in 0..<disk.size) {
            if (disk[i] != -1) {
                checksum += (disk[i] * i).toLong()
            }
        }
        return checksum
    }
}