package adventofcode2022

import scala.io.Source

object Utils {
  def readFile(fileName: String): List[String] = {
    Source.fromFile(fileName).getLines.toList
  }
}
