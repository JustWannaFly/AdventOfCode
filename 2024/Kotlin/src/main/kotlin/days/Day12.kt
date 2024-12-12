package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.datastructures.TwoDMap

fun main() {
    println("---${Utilities.INTRO} Day 12---")
    val input: List<String> = Utilities.readResource("12")
    val day5 = Day12(input)
    println("Part 1: ${day5.part1()}")
    println("Part 2: ${day5.part2()}")
}

class Day12(data: List<String>) {
    private val map = TwoDMap(data.map { row: String -> row.map { Plot(it) } })
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
        return 0
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
    class Plot(val crop: Char) {
        var areaSize = 0
        var fences = 0
    }
}