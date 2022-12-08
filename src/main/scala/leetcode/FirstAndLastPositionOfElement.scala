package leetcode

import scala.annotation.tailrec

object FirstAndLastPositionOfElement extends App {
  def findElement(nums: Array[Int], target: Int): Int = {
    @tailrec
    def binarySearch(start: Int, end: Int): Int = {
      val middle = start + (end - start) / 2

      println(s"$start $middle $end")
      if (start > end) -1
      else if (nums(middle) == target) middle
      else if (nums(middle) < target) binarySearch(middle + 1, end)
      else binarySearch(start, middle - 1)
    }
    binarySearch(0, nums.length - 1)
  }

  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    def findFirstAndLast(targetPosition: Int): Array[Int] = {
      @tailrec
      def find(start: Int, end: Int): Array[Int] = {
        if(start >= 0 && nums(start) == target) find(start - 1, end)
        else if(end <= nums.length - 1 && nums(end) == target) find(start, end + 1)
        else Array(start + 1, end - 1)
      }
      find(targetPosition, targetPosition)
    }
      val targetPosition = findElement(nums, target)
      if(targetPosition == -1) Array(-1, -1)
      else findFirstAndLast(targetPosition)
    }

  println(searchRange(Array(5, 6, 7, 7, 8, 8, 10), 6).mkString("Array(", ", ", ")"))
}
