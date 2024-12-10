import com.github.justwannafly.days.Day04
import org.junit.jupiter.api.Test

class Day04Test {

  companion object {
   val input : List<String> = listOf(
    "MMMSXXMASM",
    "MSAMXMSMSA",
    "AMXSXMAAMM",
    "MSAMASMSMX",
    "XMASAMXAMM",
    "XXAMMXXAMA",
    "SMSMSASXSS",
    "SAXAMASAAA",
    "MAMMMXMMMM",
    "MXMXAXMASX"
   )
  }
@Test
 fun part1() {
  kotlin.test.assertEquals(18, Day04(input).part1())
 }

@Test
 fun part2() {
  kotlin.test.assertEquals(0, Day04(input).part2())
 }
}