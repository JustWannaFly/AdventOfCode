package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.datastructures.TwoDMap

fun main() {
    println("---${Utilities.INTRO} Day 08---")
    val input: List<String> = Utilities.readResource("08")
    val day08 = Day08(input)
    println("Part 1: ${day08.part1()}")
    println("Part 2: ${day08.part2()}")
}

class Day08(data: List<String>) {
    private val townMap = TwoDMap(data.map { it.toCharArray().toList() })

    private val frequencies = data.map { it.toCharArray().toList() }.flatten().filter { it != '.' }.toSet()
    fun part1(): Int {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        frequencies.forEach { frequency ->
            antinodes.addAll(getAntinodes(townMap.findAll(frequency)))
        }
        return antinodes.size
    }
    fun part2(): Int {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        frequencies.forEach { frequency ->
            antinodes.addAll(getHarmonics(townMap.findAll(frequency)))
        }
        return antinodes.size
    }
    fun getAntinodes(antennas: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        if (antennas.size < 2) {
            return emptyList()
        }
        val antinodes = mutableListOf<Pair<Int, Int>>()
        antennas.subList(1, antennas.size).forEach { antinodes.addAll(getAntinodes(antennas.first(), it)) }
        antinodes.addAll(getAntinodes(antennas.subList(1, antennas.size)))
        return antinodes.filter { townMap.getOrNull(it) != null }
    }
    fun getAntinodes(ant1: Pair<Int, Int>, ant2: Pair<Int, Int>): List<Pair<Int, Int>> {
        val dx = ant1.first - ant2.first
        val dy = ant1.second - ant2.second
        return listOf(Pair(ant1.first + dx, ant1.second + dy), Pair(ant2.first - dx, ant2.second - dy))
    }
    fun getHarmonics(antennas: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        if (antennas.size < 2) {
            return emptyList()
        }
        val antinodes = mutableListOf<Pair<Int, Int>>()
        antennas.subList(1, antennas.size).forEach { antinodes.addAll(getHarmonics(antennas.first(), it)) }
        antinodes.addAll(getHarmonics(antennas.subList(1, antennas.size)))
        return antinodes
    }
    fun getHarmonics(ant1: Pair<Int, Int>, ant2: Pair<Int, Int>): List<Pair<Int, Int>> {
        val dx = ant1.first - ant2.first
        val dy = ant1.second - ant2.second
        var x = ant1.first
        var y = ant1.second
        val antinodes = mutableListOf<Pair<Int, Int>>()
        while (x >= 0 && x < townMap.getWidth()
            && y >= 0 && y < townMap.getHeight()) {
            antinodes.add(Pair(x, y))
            x -= dx
            y -= dy
        }
        x = ant2.first
        y = ant2.second
        while (x >= 0 && x < townMap.getWidth()
            && y >= 0 && y < townMap.getHeight()) {
            antinodes.add(Pair(x, y))
            x += dx
            y += dy
        }
        return antinodes
    }
}