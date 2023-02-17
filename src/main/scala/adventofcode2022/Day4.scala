package adventofcode2022

import adventofcode2022.Utils.readFile

object Day4 extends App {
  val filename = "advent_of_code_2022/day_four.txt"
  val input: List[String] = readFile(filename)

  val elfRanges: List[(String, String)] = input.map(_.split(",")).map(ranges => (ranges.head, ranges.tail.head))

  def extractInterval(range: String): (Int, Int) = {
    val splits = range.split("""-""").map(_.toInt)
    (splits.head, splits.tail.head)
  }

  def existsTotalOverlap(rangePair: (String, String)): Boolean = {
    val elf1 = extractInterval(rangePair._1)
    val elf2 = extractInterval(rangePair._2)

    if(elf1._1 >= elf2._1 && elf1._2 <= elf2._2) true
    else if(elf2._1 >= elf1._1 && elf2._2 <= elf1._2) true
    else false
  }

  println(s"Number of pairs with total overlap: [${elfRanges.count(existsTotalOverlap)}]")

  def existsOverlap(rangePair: (String, String)): Boolean = {
    val elf1 = extractInterval(rangePair._1)
    val elf2 = extractInterval(rangePair._2)

    if(existsTotalOverlap(rangePair)) true
    else if(elf1._1 >= elf2._1 && elf1._1 <= elf2._2) true
    else if(elf1._2 >= elf2._1 && elf1._2 <= elf2._2) true
    else if(elf2._1 >= elf1._1 && elf2._1 <= elf1._2) true
    else if(elf2._2 >= elf1._1 && elf2._2 <= elf1._2) true
    else false
  }

  println(s"Number of pairs with some overlap: [${elfRanges.count(existsOverlap)}]")
}
