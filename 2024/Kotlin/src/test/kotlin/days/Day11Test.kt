package days

import com.github.justwannafly.days.Day11
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day11Test {

  private val input = listOf("125 17")

@Test
 fun part1() {
  kotlin.test.assertEquals(55312, Day11(input).part1())
 }
}