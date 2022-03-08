name := "swagger-akka-http-sample-annotate-traits-test"

scalaVersion := "2.12.15"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

val akkaVersion = "2.6.17"
val akkaHttpVersion = "10.2.6"
val jacksonVersion = "2.10.0"
val swaggerVersion = "2.1.13"

libraryDependencies ++= Seq(
  "javax.ws.rs" % "javax.ws.rs-api" % "2.1.1",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.4.2",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.4.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "io.swagger.core.v3" % "swagger-core" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-annotations" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-models" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-jaxrs2" % swaggerVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "1.1.1",
  "org.slf4j" % "slf4j-simple" % "1.7.30"
)
