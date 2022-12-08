package leetcode

import scala.annotation.tailrec

object LongestPalindromicSubstring extends App {


  def getLongestPalindromicSubstring(string: String): String = {
    def findLongest(middle: Int, longest: String): String = {
      if (middle == string.length) longest
      else {
        val currentLongest = longestPalindromicWithMiddle(string, middle)
        if (currentLongest.length > longest.length) findLongest(middle + 1, currentLongest)
        else findLongest(middle + 1, longest)
      }
    }

    findLongest(0, string.head.toString)
  }

  def longestPalindromicWithMiddle(string: String, middle: Int): String = {
    @tailrec
    def getLongest(str: String, left: Int, right: Int, longest: String): String = {
      if (right >= string.length || left < 0) longest
      else if (str(left) == str(right)) getLongest(str, left - 1, right + 1, str.substring(left, right + 1))
      else longest
    }

    val evenLengthPalindrom = getLongest(string, middle, middle + 1, string.head.toString)
    val unevenLengthPalindrom = getLongest(string, middle - 1, middle + 1, string.head.toString)

    if (evenLengthPalindrom.length > unevenLengthPalindrom.length) evenLengthPalindrom else unevenLengthPalindrom
  }

  println(getLongestPalindromicSubstring("abbasbababbbbababsdfs"))
}
