scalaVersion := "2.12.7"
name := "wikipedia-page-view-agg"
organization := "com.vc"
version := "1.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.3",
  "com.typesafe.akka" %% "akka-stream" % "2.6.3",
  "com.typesafe.akka" %% "akka-http" % "10.1.11",
  "com.typesafe" % "config" % "1.4.0",
  "com.lightbend.akka" %% "akka-stream-alpakka-file" % "1.1.2",
  "joda-time" % "joda-time" % "2.10.5",
  "org.typelevel" %% "cats-core" % "2.1.0",
  "org.scalatest" %% "scalatest" % "3.1.0" % Test
)