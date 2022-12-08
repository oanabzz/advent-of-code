package interview

object TheClassicStringsProblem extends App {
  val input = List("bb", "aa", "aab", "ca", "caa", "baaa", "cca", "bbbbb", "aaaaa")

  val result = input
    .groupBy(_.toSet)
    .map(kv => kv._2.head -> kv._2.length)
  println(result)
}
