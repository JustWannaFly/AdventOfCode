package days

import com.github.justwannafly.days.Day07
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day07Test {
  private val testData = listOf(
           "190: 10 19",
           "3267: 81 40 27",
           "83: 17 5",
           "156: 15 6",
           "7290: 6 8 6 15",
           "161011: 16 10 13",
           "192: 17 8 14",
           "21037: 9 7 18 13",
           "292: 11 6 16 20"
  )

  @Test
  fun part1() {
   assertEquals(3749, Day07(testData).part1())
  }

  @Test
  fun part2() {
   assertEquals(0, Day07(testData).part2())
  }
 }