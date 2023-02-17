package adventofcode2022

import adventofcode2022.Utils.readFile

object Day7 extends App {
  val filename = "advent_of_code_2022/day_six.txt"
  val input: List[String] = readFile(filename)

  trait Resource {
    def name: String
    def size: Int
  }

  case class Directory(name: String, contents: List[Resource], size: Int = 0) extends Resource
  case class File(name: String, size: Int) extends Resource


}
