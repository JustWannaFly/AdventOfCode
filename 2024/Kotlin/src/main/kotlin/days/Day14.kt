package com.github.justwannafly.days

import com.github.justwannafly.Utilities
import java.io.File

fun main() {
    println("---${Utilities.INTRO} Day 14---")
    val input: List<String> = Utilities.readResource("14")
    val day14 = Day14(input, 101, 103)
    println("Part 1: ${day14.part1()}")
    println("Part 2:  ${day14.part2()}")
}

class Day14 (val input: List<String>, val width: Int, val height: Int) {
    fun part1(): Int {
        val robots = input.map { Robot(it) }
        val t = 100
        val midWidth = width / 2
        val midHeight = height / 2
        var q1 = 0
        var q2 = 0
        var q3 = 0
        var q4 = 0
        robots.forEach {
            it.x = getFuturePos(it.x, it.dx, width, t)
            it.y = getFuturePos(it.y, it.dy, height, t)
            if (it.x < midWidth && it.y < midHeight) {
                q1++
            } else if (it.x > midWidth && it.y < midHeight) {
                q2++
            } else if (it.x < midWidth && it.y > midHeight) {
                q3++
            } else if (it.x > midWidth && it.y > midHeight) {
                q4++
            }
        }
        return q1 * q2 * q3 * q4
    }

    fun part2(): Int {
        File("out.txt").printWriter().use { out ->
            val robots = input.map { Robot(it) }
            for (t: Int in 0..8000) {
                val coords = mutableSetOf<Pair<Int, Int>>()
                robots.forEach {
                    coords.add(Pair(it.x, it.y))
                }
                out.println("T=$t")
                getBotPic(robots).forEach { out.println(it) }
                out.println("T=$t")

                robots.forEach {
                    it.x = getFuturePos(it.x, it.dx, width, 1)
                    it.y = getFuturePos(it.y, it.dy, height, 1)
                }
            }
        }
        return 0
    }

    class Robot(data: String) {
        var x: Int = data.split(" ")[0].split("=")[1].split(",")[0].toInt()
        var y: Int = data.split(" ")[0].split("=")[1].split(",")[1].toInt()
        val dx: Int = data.split(" ")[1].split("=")[1].split(",")[0].toInt()
        val dy: Int = data.split(" ")[1].split("=")[1].split(",")[1].toInt()
    }

    fun getFuturePos(current: Int, speed: Int, limit: Int, time: Int): Int {
        var future = 0
        if (speed > 0) {
            future = (current + (speed * time)) % limit
        } else {
            future = (current - ((speed * -1) * time)) % limit
            if (future < 0) {
                future += limit
            }
        }
        return if (future == limit) 0 else future
    }
    fun getBotPic(robots: List<Robot>): List<String> {
        val picture = mutableListOf<MutableList<Char>>()
        for (y in 0 until height) {
            picture.add(mutableListOf())
            for (x in 0 until width) {
                picture[y].add('.')
            }
        }
        robots.forEach {
            picture[it.y][it.x] = '8'
        }
        return picture.map{ it.joinToString("") }
    }
}