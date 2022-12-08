package interview

object MostCommonName extends App {
  case class Student(name: String, isActive: Boolean)

  val studentList: List[Student] = List(Student("name", true), Student("name", false))
}
