package days

import com.github.justwannafly.days.Day09
import org.junit.jupiter.api.Test

 class Day09Test {
  companion object {
   val input : List<String> = listOf(
    "2333133121414131402"
   )
  }
  @Test
  fun part1() {
   kotlin.test.assertEquals(1928, Day09(input).part1())
  }

  @Test
  fun part2() {
   kotlin.test.assertEquals(2858, Day09(input).part2())
  }
}