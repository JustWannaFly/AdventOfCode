package days

import com.github.justwannafly.days.Day10
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day10Test {

  val input = listOf(
          "89010123",
          "78121874",
          "87430965",
          "96549874",
          "45678903",
          "32019012",
          "01329801",
          "10456732")
@Test
 fun part1() {
  kotlin.test.assertEquals(36, Day10(input).part1())
 }

@Test
 fun part2() {
 kotlin.test.assertEquals(0, Day10(input).part2())
 }
}