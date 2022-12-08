package day2

import scala.annotation.tailrec
import scala.io.Source

case class Instructions(direction: String, value: Int)

case class Coordinates3(depth: Int, position: Int, aim: Int)

object PilotSubmarineHarder extends App {

  val filename = "input/day_two.txt"

  private val input = Source.fromFile(filename).getLines
    .map(_.split(" "))
    .map(strings => Instructions(strings.head, strings.tail.head.toInt))
    .toList

  @tailrec
  def getCoordinates(current: Coordinates3, input: List[Instructions]): Coordinates3 = {
    if (input.isEmpty) current
    else input.head match {
      case Instructions("forward", value) => getCoordinates(Coordinates3(current.depth + (current.aim * value), current.position + value, current.aim), input.tail)
      case Instructions("up", value) => getCoordinates(Coordinates3(current.depth, current.position, current.aim - value), input.tail)
      case Instructions("down", value) => getCoordinates(Coordinates3(current.depth, current.position, current.aim + value), input.tail)
    }
  }

  private val result: Coordinates3 = getCoordinates(Coordinates3(0, 0, 0), input)
  println(result)
  println(result.depth * result.position)
}
