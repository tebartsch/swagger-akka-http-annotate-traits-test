package com.example.akka

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.RouteConcatenation
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import com.example.akka.annotateTrait.{ExampleTraitActor, ExampleTraitService}
import com.example.akka.swagger.SwaggerDocService

import scala.concurrent.ExecutionContext

object Rest extends App with RouteConcatenation {
  implicit val actorSystem: ActorSystem = ActorSystem("alpakka-samples")
  implicit val ec: ExecutionContext = actorSystem.dispatcher

  val exampleTrait = actorSystem.actorOf(Props[ExampleTraitActor])
  val routes =
    cors() (new ExampleTraitService(exampleTrait).route ~
      SwaggerDocService.routes)
  Http().newServerAt("0.0.0.0", 1111).bindFlow(routes)
}