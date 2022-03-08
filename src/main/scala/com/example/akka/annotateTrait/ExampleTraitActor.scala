package com.example.akka.annotateTrait

import akka.actor.{Actor, ActorLogging}
import io.swagger.v3.oas.annotations.media.Schema

trait Identifier {
  val id: String
}
case class IdentifierSwaggerSchema()

trait Value {
  val value: Int
}
case class ValueSwaggerSchema()

trait Event extends Identifier with Value
@Schema(name="Event", description = "combine identifier and value")
case class EventCaseClass(@Schema(name="identifier", description = "identifier of data value", implementation=classOf[String])
                          id: IdentifierSwaggerSchema,
                          @Schema(name="value", description = "data value", implementation=classOf[Int])
                          value: ValueSwaggerSchema)

@Schema(description = "response to data query", implementation = classOf[EventCaseClass])
case class Response(event: Event)

case object Request

class ExampleTraitActor extends Actor with ActorLogging {

  def receive: Receive = {
    case Request => {
      val response =
        Response(
          event = new Event { val id = "id0"; val value = 0 }
        )
      sender ! response
    }
  }
}

