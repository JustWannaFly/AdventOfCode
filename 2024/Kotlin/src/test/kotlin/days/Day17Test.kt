package days

import com.github.justwannafly.days.Day17
import org.junit.jupiter.api.Test

 class Day17Test {

  val t1 : List<String> = listOf(
   "Register A: 0",
   "Register B: 0",
   "Register C: 9",
   "",
   "Program: 2,6"
  )
  val t2 : List<String> = listOf(
   "Register A: 10",
   "Register B: 0",
   "Register C: 0",
   "",
   "Program: 5,0,5,1,5,4"
  )
  val t3 : List<String> = listOf(
   "Register A: 2024",
   "Register B: 0",
   "Register C: 0",
   "",
   "Program: 0,1,5,4,3,0"
  )
  val t4 : List<String> = listOf(
   "Register A: 0",
   "Register B: 29",
   "Register C: 0",
   "",
   "Program: 1,7"
  )
  val t5 : List<String> = listOf(
   "Register A: 0",
   "Register B: 2024",
   "Register C: 43690",
   "",
   "Program: 4,0"
  )
  val input = listOf(
   "Register A: 729",
   "Register B: 0",
   "Register C: 0",
   "",
   "Program: 0,1,5,4,3,0")
  val input2 = listOf(
   "Register A: 2024",
   "Register B: 0",
   "Register C: 0",
   "",
   "Program: 0,3,5,4,3,0"
  )

@Test
 fun part1() {
  var day17 = Day17(t1)
 day17.part1()
 kotlin.test.assertEquals(1, day17.regB)
 day17 = Day17(t2)
 day17.part1()
 kotlin.test.assertEquals("0,1,2", day17.output)
 day17 = Day17(t3)
 day17.part1()
 kotlin.test.assertEquals("4,2,5,6,7,7,7,7,3,1,0", day17.output)
 kotlin.test.assertEquals(0, day17.regA)
 day17 = Day17(t4)
 day17.part1()
 kotlin.test.assertEquals(26, day17.regB)
 day17 = Day17(t5)
 day17.part1()
 kotlin.test.assertEquals(44354, day17.regB)

 kotlin.test.assertEquals("4,6,3,5,6,3,5,2,1,0", Day17(input).part1())
}

@Test
 fun part2() {
 kotlin.test.assertEquals(117440, Day17(input2).part2())
}
}