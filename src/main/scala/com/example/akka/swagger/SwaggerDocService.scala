package com.example.akka.swagger

import com.github.swagger.akka.SwaggerHttpService
import com.example.akka.annotateTrait.ExampleTraitService

object SwaggerDocService extends SwaggerHttpService {
  override val apiClasses = Set(classOf[ExampleTraitService])
  override val host = "localhost:1111"
  //override val securitySchemeDefinitions = Map("basicAuth" -> new BasicAuthDefinition())
  override val unwantedDefinitions = Seq("Function1", "Function1RequestContextFutureRouteResult")
}