package adventofcode2022.day2

import adventofcode2022.Utils.readFile

object Day2 extends App {
  val filename = "advent_of_code_2022/day_two.txt"
  val input: List[String] = readFile(filename)

  // A rock
  // B paper
  // C scissors
  // X rock or lose
  // Y paper or draw
  // Z scissors or win

  val win = (round: (String, String)) => List(("A", "Y"), ("B", "Z"), ("C", "X")).contains(round)
  val draw = (round: (String, String)) => List(("A", "X"), ("B", "Y"), ("C", "Z")).contains(round)
  val lose = (round: (String, String)) => List(("A", "Z"), ("B", "X"), ("C", "Y")).contains(round)

  def getRoundPoints(round: (String, String)): Int = {
    if(win(round)) 6
    else if(draw(round)) 3
    else 0
  }

  def getMovePoints(move: String) = {
    move match {
      case "X" => 1
      case "Y" => 2
      case "Z" => 3
    }
  }

  val rounds: List[(String, String)] = input.map(_.split(" ")).map(arr => (arr.head, arr.tail.head))

  val points = rounds.map(round => getRoundPoints(round) + getMovePoints(round._2))
  println(s"Total points: [${points.sum}]")

  def winAgainst: String => String = {
    case "A" => "Y"
    case "B" => "Z"
    case "C" => "X"
  }

  def drawAgainst: String => String = {
    case "A" => "X"
    case "B" => "Y"
    case "C" => "Z"
  }

  def loseAgainst: String => String = {
    case "A" => "Z"
    case "B" => "X"
    case "C" => "Y"
  }

  def mapRound(round: (String, String)): (String, String) = {
    round._2 match {
      case "X" => (round._1, loseAgainst(round._1))
      case "Y" => (round._1, drawAgainst(round._1))
      case "Z" => (round._1, winAgainst(round._1))
    }
  }

  val newStrategyRounds: List[(String, String)] = rounds.map(mapRound)

  val newPoints = newStrategyRounds.map(round => getRoundPoints(round) + getMovePoints(round._2))

  println(rounds)
  println(newStrategyRounds)

  println(s"Total points with newer strategy: [${newPoints.sum}]")
}
