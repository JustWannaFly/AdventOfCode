package days

import com.github.justwannafly.days.Day12
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day12Test {

  private val testData = listOf(
           "RRRRIICCFF",
           "RRRRIICCCF",
           "VVRRRCCFFF",
           "VVRCCCJFFF",
           "VVVVCJJCFE",
           "VVIVCCJJEE",
           "VVIIICJJEE",
           "MIIIIIJJEE",
           "MIIISIJEEE",
           "MMMISSJEEE"
  )

@Test
 fun part1() {
 assertEquals(1930, Day12(testData).part1())
 }

@Test
 fun part2() {
 assertEquals(1206, Day12(testData).part2())
 }
}