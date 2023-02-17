package adventofcode2021.day4

import scala.io.Source

object Bingo extends App {
  val filename = "input/day_four.txt"

  val input = Source.fromFile(filename).getLines()

  val bingoNumbers = input.next().split(",").toList

  println(bingoNumbers)

  val matrices = input.drop(1)

  val value = matrices
    .toList
    .filter(_ != "")
    .grouped(5)
    .toList
    .map(list =>
      list
        .map(_.split(" ").filter(_.nonEmpty).map(_.toInt))
    )

  println(value)



//    .getLines.map(_.toInt).toList
}
