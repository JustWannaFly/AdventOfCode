package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import com.github.justwannafly.datastructures.TwoDMap
import com.github.justwannafly.datastructures.TwoDMap.Direction

fun main() {
    println("---${Utilities.INTRO} Day 06---")
    val input: List<String> = Utilities.readResource("06")
    val day5 = Day06(input)
    println("Part 1: ${day5.part1()}")
    println("Part 2: ${day5.part2()}")
}

class Day06(private val data: List<String>) {
    private var map = TwoDMap(data.map { row: String -> row.map {Space(it)} })
    private var guard = Guard(
        x = data.find { it.contains('^')}!!.indexOf('^'),
        y = data.indexOf(data.find { it.contains('^') }!!),
        dir = Direction.North
    )
    fun resetMap() {
        map = TwoDMap(data.map { row: String -> row.map {Space(it)} })
        guard = Guard(
            x = data.find { it.contains('^')}!!.indexOf('^'),
            y = data.indexOf(data.find { it.contains('^') }!!),
            dir = Direction.North
        )
    }

    fun part1(): Int {
        var nextSpace = map.getNeighbor(guard.x, guard.y, guard.dir)
        while (nextSpace != null) {
            if (nextSpace.canWalk) {
                guard.setLocation(map.getNeighborCoords(guard.x, guard.y, guard.dir))
                map.get(guard.x, guard.y).passed = true
            } else {
                turnGuard()
            }
            nextSpace = map.getNeighbor(guard.x, guard.y, guard.dir)
        }
        return map.count { it.passed }
    }
    fun part2(): Int {
        var loopCount = 0

        var y = 0
        while (y < map.getHeight()) {
            var x = 0
            while (x < map.getWidth()) {
                if (map.get(x, y).canWalk) {
                    resetMap()
                    map.get(x, y).canWalk = false
                    var nextSpace = map.getNeighbor(guard.x, guard.y, guard.dir)
                    while (nextSpace != null) {
                        if (nextSpace.passed && nextSpace.passedDirs.contains(guard.dir)) {
                            loopCount++
                            break
                        } else if (nextSpace.canWalk) {
                            guard.setLocation(map.getNeighborCoords(guard.x, guard.y, guard.dir))
                            map.get(guard.x, guard.y).passed = true
                            map.get(guard.x, guard.y).passedDirs.add(guard.dir)
                        } else {
                            turnGuard()
                        }
                        nextSpace = map.getNeighbor(guard.x, guard.y, guard.dir)
                    }
                }
                x++
            }
            y++
        }
        return loopCount
    }


    private fun turnGuard() {
        when (guard.dir) {
            Direction.North -> { guard.dir = Direction.East }
            Direction.East -> { guard.dir = Direction.South }
            Direction.South -> { guard.dir = Direction.West }
            Direction.West -> { guard.dir = Direction.North }
            else -> throw RuntimeException("Unexpected direction")
        }
    }

    class Space(data: Char) {
        var canWalk = data != '#'
        var passed = data == '^'
        val passedDirs = mutableSetOf<Direction>()
    }
    class Guard(var x: Int, var y: Int, var dir: Direction) {
        fun setLocation(location: Pair<Int, Int>) {
            x = location.first
            y = location.second
        }
    }
}