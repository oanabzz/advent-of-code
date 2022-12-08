package day1

import scala.annotation.tailrec
import scala.io.Source

object Submarine extends App {
  val filename = "input/day_one.txt"

  private val input: List[Int] = Source.fromFile(filename).getLines.map(_.toInt).toList

  var increases = 0
  var current = input.head

  for(number <- input.tail) {
    if(number > current) {
      increases = increases + 1
    }
    current = number
  }

  @tailrec
  def getNumberOfIncreases(inc: Int, current: Int, list: List[Int]): Int = {
    if(list.isEmpty) inc
    else if(current < list.head) getNumberOfIncreases(inc +1, list.head, list.tail)
    else getNumberOfIncreases(inc, list.head, list.tail)
  }

  println(getNumberOfIncreases(0, input.head, input.tail))

  println(increases)

}
