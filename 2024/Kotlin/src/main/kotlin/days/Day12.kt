package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.datastructures.TwoDMap
import com.github.justwannafly.datastructures.TwoDMap.Direction

fun main() {
    println("---${Utilities.INTRO} Day 12---")
    val input: List<String> = Utilities.readResource("12")
    val day12 = Day12(input)
    println("Part 1: ${day12.part1()}")
    println("Part 2: ${day12.part2()}")
}

class Day12(private val data: List<String>) {
    private var map = TwoDMap(data.map { row: String -> row.map { Plot(it) } })
    fun part1(): Int {
        var sum = 0

        var y = 0
        while (y < map.getHeight()) {
        var x = 0
            while (x < map.getWidth()) {
                val plot = map.get(x,y)
                val neighbors = map.getNeighbors(x,y, TwoDMap.cardinals)
                plot.fences = 4 - neighbors.count { it.crop == plot.crop }
                if (plot.areaSize == 0) {
                    findAndMarkArea(x, y)
                }
                sum += plot.areaSize * plot.fences
                x++
            }
            y++
        }
        return sum
    }
    fun part2(): Int {
        resetMap()
        var sum = 0

        var y = 0
        while (y < map.getHeight()) {
            var x = 0
            while (x < map.getWidth()) {
                val plot = map.get(x,y)
                val neighbors = map.getNeighbors(x,y, TwoDMap.cardinals)
                plot.fences = 4 - neighbors.count { it.crop == plot.crop }
                if (plot.areaSize == 0) {
                    findAndMarkArea(x, y)
                    sum += plot.areaSize * getSideCount(Pair(x, y))
                }
                x++
            }
            y++
        }
        return sum
    }
    private fun findAndMarkArea(x: Int, y: Int): Int {
        val plots = getMatchingPlots(x, y)
        plots.forEach { it.areaSize = plots.size }
        return plots.size
    }
    private fun getMatchingPlots(x: Int, y: Int): List<Plot> {
        val plot = map.get(x,y)
        plot.areaSize = -1
        val matchingPlots = mutableListOf(plot)
        map.getNeighborCoords(x, y, TwoDMap.cardinals)
            .forEach {
                if (map.get(it).crop == plot.crop && map.get(it).areaSize == 0)
                matchingPlots.addAll(getMatchingPlots(it.first, it.second))
            }
        return matchingPlots
    }
    private fun getSideCount(coords: Pair<Int, Int>): Int {
        val areaCoords = getAllAreaCoords(coords.first, coords.second)
        var sideCount = 0
        // Ray-cast across rows
        for (y: Int in areaCoords.minOf { it.second } until areaCoords.maxOf { it.second } + 1 ) {
            var isNorth = false
            var isSouth = false
            for (x: Int in areaCoords.minOf { it.first } until areaCoords.maxOf { it.first } +  1 ) {
                if (areaCoords.contains(Pair(x, y))) {
                    if (!areaCoords.contains(map.getNeighborCoords(x, y, Direction.North))) {
                        if (!isNorth) {
                            sideCount++
                        }
                        isNorth = true
                    } else {
                        isNorth = false
                    }
                    if (!areaCoords.contains(map.getNeighborCoords(x, y, Direction.South))) {
                        if (!isSouth) {
                            sideCount++
                        }
                        isSouth = true
                    } else {
                        isSouth = false
                    }
                } else {
                    isNorth = false
                    isSouth = false
                }
            }
        }
        // Ray-cast across columns
        for (x: Int in areaCoords.minOf { it.first } until areaCoords.maxOf { it.first } + 1 ) {
            var isEast = false
            var isWest = false
            for (y: Int in areaCoords.minOf { it.second } until areaCoords.maxOf { it.second } + 1 ) {
                if (areaCoords.contains(Pair(x, y))) {
                    if (!areaCoords.contains(map.getNeighborCoords(x, y, Direction.East))) {
                        if (!isEast) {
                            sideCount++
                        }
                        isEast = true
                    } else {
                        isEast = false
                    }
                    if (!areaCoords.contains(map.getNeighborCoords(x, y, Direction.West))) {
                        if (!isWest) {
                            sideCount++
                        }
                        isWest = true
                    } else {
                        isWest = false
                    }
                } else {
                    isEast = false
                    isWest = false
                }
            }
        }
        return sideCount
    }
    private fun getAllAreaCoords(x: Int, y: Int): List<Pair<Int, Int>> {
        val plot = map.get(x,y)
        plot.sides = -1
        val matchingPlots = mutableListOf(Pair(x, y))
        map.getNeighborCoords(x, y, TwoDMap.cardinals)
            .forEach {
                if (map.get(it).crop == plot.crop && map.get(it).sides == 0)
                    matchingPlots.addAll(getAllAreaCoords(it.first, it.second))
            }
        return matchingPlots
    }
    private fun resetMap() {
        map = TwoDMap(data.map { row: String -> row.map { Plot(it) } })
    }
    class Plot(val crop: Char) {
        var areaSize = 0
        var fences = 0
        var sides = 0
    }
}