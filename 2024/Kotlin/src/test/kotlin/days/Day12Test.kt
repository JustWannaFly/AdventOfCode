package days

import com.github.justwannafly.days.Day12
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day12Test {

  private val testData = listOf(
   "AAAA",
   "BBCD",
   "BBCC",
   "EEEC"
  )

@Test
 fun part1() {
 assertEquals(140, Day12(testData).part1())
 }

@Test
 fun part2() {
 assertEquals(80, Day12(testData).part2())
 }
}