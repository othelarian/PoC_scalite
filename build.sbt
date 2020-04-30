scalaVersion := "2.13.1"

name := "PoCScalite"
organization := "play.othy.scala"
version := "1.0"

//libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.23.1"
libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core" % "0.8.8",
  "org.tpolecat" %% "doobie-hikari" % "0.8.8",
  "org.tpolecat" %% "doobie-specs2" % "0.8.8"
)
