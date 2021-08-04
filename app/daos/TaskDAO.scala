package daos

import com.google.inject.Inject
import models.{Task, taskTableDef}
import slick.jdbc.MySQLProfile.api._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class Tasks @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

	val tasks = TableQuery[taskTableDef]

	def add(task: Task): Future[String] = {
		dbConfig.db.run(tasks += task).map(res => "Task successfully added")
	}

	def complete(task: Task): Future[Int] = {
		dbConfig.db.run(tasks.filter(_.id === task.id).map(_.completed).update(task.completed))
	}

	def delete(id: Long): Future[Int] = {
		dbConfig.db.run(tasks.filter(_.id === id).delete)
	}

	def listAll: Future[Seq[Task]] = {
		dbConfig.db.run(tasks.result)
	}

}
