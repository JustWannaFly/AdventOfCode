package days

import com.github.justwannafly.days.Day05
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day05Test {

  val testData = listOf(
          "47|53",
          "97|13",
          "97|61",
          "97|47",
          "75|29",
          "61|13",
          "75|53",
          "29|13",
          "97|29",
          "53|29",
          "61|53",
          "97|53",
          "61|29",
          "47|13",
          "75|47",
          "97|75",
          "47|61",
          "75|61",
          "47|29",
          "75|13",
          "53|13",
          "",
          "75,47,61,53,29",
          "97,61,53,29,13",
          "75,29,13",
          "75,97,47,61,53",
          "61,13,29",
          "97,13,75,29,47")

@Test
 fun part1() {
  kotlin.test.assertEquals(143, Day05(testData).part1())
 }

@Test
 fun part2() {
 kotlin.test.assertEquals(123, Day05(testData).part2())
 }
}