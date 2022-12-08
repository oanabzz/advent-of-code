package day1

import scala.annotation.tailrec
import scala.io.Source

object SubmarineHarder extends App {
  val filename = "input/day_one.txt"

  val input: List[Int] = Source.fromFile(filename).getLines.map(_.toInt).toList

  val input2 = List(100,125,124,127,141,145,160,161,178,185)
  @tailrec
  def getMeasurements(sum: Int, position: Int, input: List[Int], measurements: List[Int]): List[Int] = {
    if(input.length < 3) measurements
    else if(position == 2) getMeasurements(0, 0, input.tail, measurements :+ (sum + input.head))
    else getMeasurements(sum + input.head, position + 1, input.tail, measurements)
  }

  @tailrec
  def getMeasurementsCorrectly(el1: Int, el2: Int, el3: Int, input: List[Int], measurements: List[Int]): List[Int] = {
    if(input.length < 1) measurements :+ ((el1 + el2 + el3))
    else getMeasurementsCorrectly(el2, el3, input.head, input.tail, measurements :+ (el1 + el2 + el3))
  }

  println(getMeasurementsCorrectly(input2.head, input2.tail.head, input2.tail.tail.head, input2.tail.tail.tail, List()))

  @tailrec
  def getNumberOfIncreases(inc: Int, current: Int, list: List[Int]): Int = {
    if(list.isEmpty) inc
    else if(current < list.head) getNumberOfIncreases(inc +1, list.head, list.tail)
    else getNumberOfIncreases(inc, list.head, list.tail)
  }

  println(getMeasurements(0, 0, input2, List()))

  val measurements = getMeasurementsCorrectly(input.head, input.tail.head, input.tail.tail.head, input.tail.tail.tail, List())
  println(getNumberOfIncreases(0, measurements.head, measurements.tail))
}
