package day2

import scala.annotation.tailrec
import scala.io.Source
import scala.runtime.IntRef

sealed case class Instruction(direction: String, value: Int)
sealed case class Coordinates(depth: Int, position: Int)

object PilotSubmarine extends App {
  val filename = "input/day_two.txt"

  private val input = Source.fromFile(filename).getLines
    .map(_.split(" "))
    .map(strings => Instruction(strings.head, strings.tail.head.toInt))
    .toList

  @tailrec
  def getCoordinates(current: Coordinates, input: List[Instruction]): Coordinates = {
    if(input.isEmpty) current
    else input.head match {
      case Instruction("forward", value) => getCoordinates(Coordinates(current.depth, current.position + value), input.tail)
      case Instruction("up", value) => getCoordinates(Coordinates(current.depth - value, current.position), input.tail)
      case Instruction("down", value) => getCoordinates(Coordinates(current.depth + value, current.position), input.tail)
    }
  }

  private val result: Coordinates = getCoordinates(Coordinates(0, 0), input)
  println(result)
  println(result.depth * result.position)
}
