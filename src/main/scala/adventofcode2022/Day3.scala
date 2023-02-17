package adventofcode2022

import adventofcode2022.Utils.readFile

object Day3 extends App {
  val filename = "advent_of_code_2022/day_three.txt"
  val input: List[String] = readFile(filename)

  val compartments: List[(String, String)] = input.map(backpack => backpack.splitAt(backpack.length / 2))
  val commonGoods: List[String] = compartments.map(comp => (comp._1 intersect comp._2).distinct)

  def score(item: String): Int = {
    if(item.head <= 'z' && item.head >= 'a') item.head - 'a' + 1
    else 26 + item.toLowerCase.head - 'a' + 1
  }

  println(s"Total sum of priorities of common goods: [${commonGoods.map(score).sum}]")

  val elfGroupBadges: List[String] = input.grouped(3).map(elfGroup => (elfGroup.head intersect elfGroup.tail.head intersect elfGroup.tail.tail.head).distinct).toList
  println(s"Total sum of elf group badges: [${elfGroupBadges.map(score).sum}]")
}
