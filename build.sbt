
resolvers += Resolver.sonatypeRepo("snapshots")
organization := "martin"
name := "value-transactions"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.3"

val Http4sVersion = "0.17.0-RC1"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.1",
  "org.http4s" %% "rho-swagger" % "0.17.0-M3",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "io.github.martintrojer" % "atom-scala_2.10" % "0.1-SNAPSHOT",
  "io.circe" %% "circe-core" % "0.8.0",
  "io.circe" %% "circe-generic" % "0.8.0",
  "io.circe" %% "circe-literal" % "0.8.0",
  "io.circe" %% "circe-parser" % "0.8.0",
  "com.chuusai" %% "shapeless" % "2.3.2",
  compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)
