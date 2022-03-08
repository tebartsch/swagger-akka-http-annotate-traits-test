package com.example.akka.annotateTrait

import akka.actor.{Actor, ActorLogging}
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "identifier of data value")
trait Identifier {
  val id: String
}

@Schema(description = "value")
trait Value {
  val value: Int
}

@Schema(description = "combine identifier and value")
trait Event extends Identifier with Value

@Schema(description = "response to data query")
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

