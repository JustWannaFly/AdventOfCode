package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.datastructures.TwoDMap

fun main() {
    println("---${Utilities.INTRO} Day 15---")
    val input: List<String> = Utilities.readResource("15")
    val day15 = Day15(input)
    println("Part 1: ${day15.part1()}")
    println("Part 2:  ${day15.part2()}")
}

class Day15(private val input: List<String>) {
    private lateinit var warehouse: TwoDMap<Char>
    private val robit = '@'
    private val wall = '#'
    private val crate = 'O'
    private val empty = '.'
    init {
        initMap()
    }
    fun part1(): Int {
        val actions = input.filter { it.contains('v') }.joinToString("")
        var robitPos = warehouse.findAll(robit)[0]
        actions.forEach {
            when (it) {
                '^' -> robitPos = move(TwoDMap.Direction.North, robitPos)
                '>' -> robitPos = move(TwoDMap.Direction.East, robitPos)
                'v' -> robitPos = move(TwoDMap.Direction.South, robitPos)
                '<' -> robitPos = move(TwoDMap.Direction.West, robitPos)
            }
        }
        var sum = 0
        for (y: Int in 0 until warehouse.getHeight()) {
            for (x: Int in 0 until warehouse.getWidth()) {
                if (warehouse.get(x, y) == crate) {
                    sum += ((y * 100) + x)
                }
            }
        }
        return sum
    }
    fun part2(): Int {
        return 0
    }
    fun initMap() {
        val mapLines = mutableListOf<String>()
        var mapDone = false
        input.forEach { line ->
            if (line.isEmpty() || mapDone) {
                mapDone = true
            } else {
                mapLines.add(line)
            }
        }
        warehouse = TwoDMap(mapLines.map{ it.toCharArray().toList() })
    }
    fun move(dir: TwoDMap.Direction, robitPos: Pair<Int, Int>): Pair<Int, Int> {
        var found = warehouse.getNeighbor(robitPos, dir)
        var step = 0
        while (found != empty) {
            step++
            found = warehouse.getNeighbor(robitPos, dir, step)
            if (found == wall) {
                return robitPos
            }
        }
        if (step > 1) {
            val emptyCoords = warehouse.getNeighborCoords(robitPos, dir, step)
            warehouse.set(emptyCoords, crate)
        }
        val newPos = warehouse.getNeighborCoords(robitPos, dir)
        warehouse.set(newPos, robit)
        warehouse.set(robitPos, empty)
        return newPos
    }
}