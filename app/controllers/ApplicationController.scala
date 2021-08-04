package controllers

import play.api._
import play.api.mvc._
import models._
import javax.inject._
import Service.TaskService
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._

@Singleton
class ApplicationController @Inject()(cc: ControllerComponents, taskService: TaskService) extends AbstractController(cc) with Logging {

	def index() = Action.async { implicit request: Request[AnyContent] =>
		taskService.listAllTasks map { task =>
			Ok(Json.toJson(task))
		}
	}

	def addTask() = Action(parse.json[Task]) { implicit request =>
		val newTask = Task(0, request.body.name, request.body.completed)
		taskService.addTask(newTask)
		Ok(Json.obj("Status" -> "OK"))
	}

	def deleteTask() = Action(parse.json[Task]) { implicit request =>
		taskService.deleteTask(request.body.id)
		Ok(Json.obj("Status" -> "Ok"))
	}

	def completeTask() = Action(parse.json[Task]) { implicit request =>
		val data = request.body
		val task = Task(data.id, data.name, data.completed)
		taskService.completeTask(task)
		Ok(Json.obj("Status" -> "OK"))
	}
}
