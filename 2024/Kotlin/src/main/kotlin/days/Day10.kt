package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.datastructures.TwoDMap

fun main() {
    println("---${Utilities.INTRO} Day 10---")
    val input: List<String> = Utilities.readResource("10")
    val day10 = Day10(input)
    println("Part 1: ${day10.part1()}")
    println("Part 2: ${day10.part2()}")
}

class Day10(
    private val input: List<String>,
) {
    companion object {
        val cardinalDirs = listOf(TwoDMap.Direction.North,
            TwoDMap.Direction.East,
            TwoDMap.Direction.South,
            TwoDMap.Direction.West)
    }
    val data: TwoDMap<Int> = TwoDMap(input.map { line -> line.toCharArray().toList().map { Utilities.charToInt(it) } } )

    fun part1(): Int {
        var sum = 0
        var y = 0
        while (y < data.getHeight()) {
            var x = 0
            while (x < data.getWidth()) {
                if (data.get(x, y) == 0) {
                    sum += getConnectedPeakCoords(x, y).size
                }
                x++
            }
            y++
        }
        return sum
    }
    fun part2(): Int {
        return 0
    }
    fun getConnectedPeakCoords(x: Int, y: Int): Set<Pair<Int, Int>> {
        val stop = data.get(x, y)
        val peakCoords = mutableSetOf<Pair<Int, Int>>()
        if (stop == 9) {
            peakCoords.add(Pair(x, y))
        }
        cardinalDirs.forEach {
            if (data.getNeighbor(x, y, it) == stop + 1) {
                val neighborCoords = data.getNeighborCoords(x, y, it)
                peakCoords.addAll(getConnectedPeakCoords(neighborCoords.first, neighborCoords.second))
            }
        }
        return peakCoords;
    }
}