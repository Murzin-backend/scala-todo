package Service

import com.google.inject.Inject
import daos.Tasks
import models.Task

import scala.concurrent.Future

class TaskService @Inject()(tasks: Tasks) {

	def addTask(task: Task): Future[String] = {
		tasks.add(task)
	}

	def deleteTask(id: Long): Future[Int] = {
		tasks.delete(id)
	}

	def listAllTasks: Future[Seq[Task]] = {
		tasks.listAll
	}

	def completeTask(task: Task): Future[Int] = {
		tasks.complete(task)
	}
}
