package days

import com.github.justwannafly.days.Day06
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day06Test {
  private val testData = listOf(
   "....#.....", 
   ".........#", 
   "..........", 
   "..#.......", 
   ".......#..", 
   "..........", 
   ".#..^.....", 
   "........#.", 
   "#.........", 
   "......#..."
  )
@Test
 fun part1() {
  assertEquals(41, Day06(testData).part1())
 }

@Test
 fun part2() {
 assertEquals(6, Day06(testData).part2())
 }
}