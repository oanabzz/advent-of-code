package adventofcode2022.day1

import adventofcode2022.Utils.readFile

import scala.annotation.tailrec

object Day1 extends App {
  val filename = "advent_of_code_2022/day_one.txt"
  val input: List[String] = readFile(filename)

  @tailrec
  def splitList(currentInput: List[String], partialResult: List[List[String]]): List[List[String]] = {
    if (!currentInput.contains("")) partialResult ++ List(currentInput)
    else {
      val splitInput = currentInput.span(_.nonEmpty)
      splitList(splitInput._2.tail, partialResult ++ List(splitInput._1))
    }
  }

  val elvesCalories = splitList(input, List())
    .map(_.map(_.toInt))
    .map(_.sum)
  val maxCalories: Int = elvesCalories.max

  println(s"Maximum number of calories: $maxCalories")

  val topThreeElves = elvesCalories.sortBy(-_).take(3)

  println(s"The top three elves are: [$topThreeElves], with total calorie sum of [${topThreeElves.sum}]")
}
