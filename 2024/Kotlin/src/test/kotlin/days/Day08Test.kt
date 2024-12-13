package days

import com.github.justwannafly.days.Day08
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day08Test {

  private val testData = listOf(
           "............",
           "........0...",
           ".....0......",
           ".......0....",
           "....0.......",
           "......A.....",
           "............",
           "............",
           "........A...",
           ".........A..",
           "............",
           "............"
  )

@Test
 fun part1() {
 assertEquals(14, Day08(testData).part1())
 }

@Test
 fun part2() {
 assertEquals(34, Day08(testData).part2())
 }
}