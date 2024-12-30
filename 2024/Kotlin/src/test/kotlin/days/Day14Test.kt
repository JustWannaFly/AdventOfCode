package days

import com.github.justwannafly.days.Day14
import org.junit.jupiter.api.Test

 class Day14Test {
  
  val input : List<String> = listOf(
   "p=0,4 v=3,-3",
   "p=6,3 v=-1,-3",
   "p=10,3 v=-1,2",
   "p=2,0 v=2,-1",
   "p=0,0 v=1,3",
   "p=3,0 v=-2,-2",
   "p=7,6 v=-1,-3",
   "p=3,0 v=-1,-2",
   "p=9,3 v=2,3",
   "p=7,3 v=-1,2",
   "p=2,4 v=2,-3",
   "p=9,5 v=-3,-3"
  )

  @Test
  fun part1() {
   kotlin.test.assertEquals(12, Day14(input, 11, 7).part1())
  }

  @Test
  fun part2() {
   kotlin.test.assertEquals(0, Day14(input, 11, 7).part2())
  }
}