package adventofcode2022

import adventofcode2022.Utils.readFile

object Day5 extends App {
  val filename = "advent_of_code_2022/day_five.txt"
  val input: List[String] = readFile(filename)

  // get initial stack config
  val initialCrateConfig = input
    .filter(_.contains("["))
    .map(line => line
      .grouped(4)
      .map(_.replaceAll("[^A-Za-z0-9]", ""))
    )
    .map(_.toList)
  val noOfStacks = initialCrateConfig.map(_.length).max

  val initialStackConfig: List[List[String]] = initialCrateConfig
    .map(line => (line ++ Seq.fill(noOfStacks)("")).take(noOfStacks))
    .transpose
    .map(_.filterNot(_.isEmpty))
    .map(_.reverse)

  // get array of moves
  case class Move(count: Int, source: Int, dest: Int)

  val movesPattern = "move ([0-9]+) from ([0-9]+) to ([0-9]+)"
  val movesRegex = movesPattern.r

  val movesArray: List[Move] = input
    .filter(_.matches(movesPattern))
    .map(moveText => {
      val movesRegex(count, source, dest) = moveText
      Move(count.toInt, source.toInt - 1, dest.toInt - 1)
    })

  def applyMove9000(cratesStacks: List[List[String]], move: Move): List[List[String]] = {
    val toBeMoved = cratesStacks(move.source).takeRight(move.count)
    val intermediateStackConfig = cratesStacks.zipWithIndex.map {
      case (stack, move.source) => stack.take(stack.length - move.count)
      case (stack, move.dest) => stack ++ toBeMoved.reverse
      case (stack, _) => stack
    }
    intermediateStackConfig
  }

  def applyMove9001(cratesStacks: List[List[String]], move: Move): List[List[String]] = {
    val toBeMoved = cratesStacks(move.source).takeRight(move.count)
    val intermediateStackConfig = cratesStacks.zipWithIndex.map {
      case (stack, move.source) => stack.take(stack.length - move.count)
      case (stack, move.dest) => stack ++ toBeMoved
      case (stack, _) => stack
    }
    intermediateStackConfig
  }

  def moveCratesAround(applyMove: (List[List[String]], Move) => List[List[String]])(cratesStacks: List[List[String]], moves: List[Move]): List[List[String]] = {
    if(moves.isEmpty) cratesStacks
    else moveCratesAround(applyMove)(applyMove(cratesStacks, moves.head), moves.tail)
  }

  println(s"Initial stack config: $initialStackConfig")
  val finalCrateConfigWith9000: List[List[String]] = moveCratesAround(applyMove9000)(initialStackConfig, movesArray)
  println(s"Crates on top with Crate Mover 9000: ${finalCrateConfigWith9000.map(_.last).mkString("")}")

  val finalCrateConfigWith9001: List[List[String]] = moveCratesAround(applyMove9001)(initialStackConfig, movesArray)
  println(s"Crates on top with Crate Mover 9001: ${finalCrateConfigWith9001.map(_.last).mkString("")}")
}
