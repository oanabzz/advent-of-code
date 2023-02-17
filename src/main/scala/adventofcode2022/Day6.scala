package adventofcode2022

import adventofcode2022.Utils.readFile

object Day6 extends App {
  val filename = "advent_of_code_2022/day_six.txt"
  val input: String = readFile(filename).head

  def getMarker(input: IndexedSeq[(Char, Int)], markerLength: Int): Int = {
    val maybeMarker = input.take(markerLength).map(_._1).mkString
    if(maybeMarker.distinct.equals(maybeMarker)) input.head._2 + markerLength
    else getMarker(input.tail, markerLength)
  }

  println(s"Package Marker found after [${getMarker(input.zipWithIndex, 4)}] characters")

  println(s"Message Marker found after [${getMarker(input.zipWithIndex, 14)}] characters")


}
