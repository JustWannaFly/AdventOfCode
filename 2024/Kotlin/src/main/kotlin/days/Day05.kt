package com.github.justwannafly.days

import com.github.justwannafly.Utilities

fun main() {
    println("---${Utilities.INTRO} Day 05---")
    val input: List<String> = Utilities.readResource("05")
    val day5 = Day05(input)
    println("Part 1: ${day5.part1()}")
    println("Part 2: ${day5.part2()}")
}

class Day05(private val input: List<String>) {

    private val orderPairs: List<Pair<Int, Int>> = input.filter { it.contains('|') }
        .map { Pair(it.split('|')[0].toInt(), it.split('|')[1].toInt()) }
    private val updatePages: List<List<Int>> = input.filter { it.contains(',') }
        .map { it.split(',') }.map { it.map(String::toInt) }

    fun List<Int>.before(page: Int) = orderPairs
    .filter { it.second == page && it.first in this }
    .map { it.first }


    fun part1(): Int {
        return updatePages.filter { isRightOrder(it) }.sumOf { it[(it.size / 2)] }
    }
    fun part2(): Int {
        return updatePages.filter { !isRightOrder(it) }.map{ it.sortedBy { page -> it.before(page).size }}.sumOf { it[(it.size / 2)] }
    }
    fun isRightOrder(pages: List<Int>): Boolean {
        orderPairs.forEach {
            if (pages.contains(it.first) && pages.contains(it.second)) {
                if (pages.indexOf(it.first) > pages.indexOf(it.second)) {
                    return false
                }
            }
        }
        return true
    }
}