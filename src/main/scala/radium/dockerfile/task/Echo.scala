package radium.dockerfile.task

import cats.data._
import cats.implicits._
import radium.dockerfile.Config
import radium.dockerfile.statement._

case class Echo(val message: String) extends Task with GenerateRunStatement with GenericCommand {

  override def command: Command = s"echo '${message}'"

}

object Echo extends TaskCreator {

  override def supportedTaskNames: Seq[TaskName] = Seq("echo")

  def message = arg[String].required

  override def createTask(yaml: Yaml)(implicit config: Config): ValidatedTask = {
    message.parse(yaml).map(Echo.apply)
  }
}