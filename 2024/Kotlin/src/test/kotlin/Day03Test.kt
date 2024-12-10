import com.github.justwannafly.Day03
import org.junit.jupiter.api.Test

class Day03Test {

  companion object {
      val testData = listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
      val testData2 = listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
  }

@Test
 fun part1() {
  kotlin.test.assertEquals(161, Day03(testData).part1())
 }

@Test
 fun part2() {
     kotlin.test.assertEquals(48, Day03(testData2).part2())
 }
}