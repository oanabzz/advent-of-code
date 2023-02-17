package adventofcode2021.day3

import scala.io.Source

object SubmarineBinary extends App {
  val filename = "input/day_three.txt"

  val input: List[List[Int]] = Source.fromFile(filename).getLines
    .map(_.toList)
    .map(_.map(_.asDigit))
    .toList

  def sumFunction: (List[Int], List[Int]) => List[Int] = {
    (l1, l2) => (l1, l2).zipped.map(_ + _)
  }

  def getIntFromBinaryList(binaryList: List[Int]): Int = {
    Integer.parseInt(binaryList.mkString, 2)
  }

  def getAggregatedValues(inputList: List[List[Int]]): List[Int] = {
    inputList.foldRight(List.fill(12)(0))(sumFunction)
  }


  def getGammaAndEpsilonRate: (Int, Int) = {
    val gammaRate = getAggregatedValues(input).map(value =>
      if (value > input.length / 2) 1
      else 0
    )

    val epsilonRate = gammaRate.map(value =>
      if (value == 1) 0 else 1)

    (getIntFromBinaryList(gammaRate), getIntFromBinaryList(epsilonRate))
  }

  val (epsilonRateDecimal, gammaRateDecimal) = getGammaAndEpsilonRate
  println(epsilonRateDecimal * gammaRateDecimal)




  val aggregatedValues = getAggregatedValues(input)
  println(aggregatedValues)

  def findOxygenRate(currentInput: List[List[Int]], position: Int): List[Int] = {
    if (currentInput.length == 1) currentInput.head
    else {
      val mostCommonDigit = if (getAggregatedValues(currentInput)(position) >= currentInput.length / 2) 1 else 0
      val newList = filterList(currentInput, position, (digit => digit == mostCommonDigit))
      findOxygenRate(newList, position + 1)
    }
  }

  def findCO2Rate(currentInput: List[List[Int]], position: Int): List[Int] = {
    if (currentInput.length == 1) currentInput.head
    else {
      val leastCommonDigit = if (getAggregatedValues(currentInput)(position) < currentInput.length / 2) 0 else 1
      val newList = filterList(currentInput, position, (digit => digit == leastCommonDigit))
      findCO2Rate(newList, position + 1)
    }
  }

  def filterList(list: List[List[Int]], position: Int, digitCondition: Int => Boolean): List[List[Int]] = {
    list.filter(binaryList => digitCondition(binaryList(position)))
  }

  val oxygenRate = findOxygenRate(input, 0)
  val co2Rate = findCO2Rate(input, 0)
  println(oxygenRate)
  println(co2Rate)
  val oxygenRateDecimal: Int = getIntFromBinaryList(oxygenRate)
  val co2RateDecimal: Int = getIntFromBinaryList(co2Rate)
  println(oxygenRateDecimal)
  println(co2RateDecimal)

  println(oxygenRateDecimal * co2RateDecimal)
}
