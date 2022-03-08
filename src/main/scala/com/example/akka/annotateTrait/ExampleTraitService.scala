package com.example.akka.annotateTrait

import akka.actor.ActorRef
import akka.http.scaladsl.server.{Directives, Route}
import akka.pattern.ask
import akka.util.Timeout
import com.example.akka.DefaultJsonFormats
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import spray.json.{JsNumber, JsObject, JsString, JsValue, RootJsonFormat, enrichAny}

import javax.ws.rs.{GET, Path}
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

@Path("request")
class ExampleTraitService(hello: ActorRef)(implicit executionContext: ExecutionContext)
  extends Directives with DefaultJsonFormats {

  implicit val timeout: Timeout = Timeout(2.seconds)

  implicit object IdentifierJsonFormat extends RootJsonFormat[Event] {
    def write(event: Event): JsValue =
      JsObject(
        "id" -> new JsString(event.id),
        "value" -> new JsNumber(event.value),
      )

    def read(json: JsValue): Event = ???
  }
  implicit object ResponseJsonFormat extends RootJsonFormat[Response] {
    def write(response: Response): JsValue = response.event.toJson

    def read(json: JsValue): Response = ???
  }

  val route: Route = getTestTrait

  @GET
  @Operation(
    responses = Array(
      new ApiResponse(
        content = Array(
          new Content(
            schema = new Schema(implementation = classOf[Response]))))))
  def getTestTrait: Route =
    path("request") {
      get {
        complete {
            (hello ? Request).mapTo[Response]
        }
      }
    }
}