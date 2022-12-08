object Test extends App {
  println(Array(1, 1, 0, 1, 1, 1).scanLeft(0)((acc, i) => if (i == 0) 0 else acc + i).mkString("Array(", ", ", ")"))
}
