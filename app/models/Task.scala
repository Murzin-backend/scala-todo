package models

import slick.jdbc.MySQLProfile.api._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

case class Task(id: Long = 0, name: String = "unknown", completed: Boolean = false)

class taskTableDef(tag: Tag) extends Table[Task](tag, "task") {
	def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
	def name = column[String]("name")
	def completed = column[Boolean]("completed")

	override def * = (id, name, completed) <> ((Task.apply _).tupled, Task.unapply)
}

trait TaskJson {
	implicit val writes: Writes[Task] = (task: Task) => Json.obj(
		"id" -> task.id,
		"name" -> task.name,
		"completed" -> task.completed
	)

	implicit val reads: Reads[Task] = (
		(__ \ "id").read[Long] and
		((__ \ "name").read[String] or Reads.pure("unknown")) and
		((__ \ "completed").read[Boolean] or Reads.pure(false))
	)(Task.apply _)
}


object Task extends TaskJson
