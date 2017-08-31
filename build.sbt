
resolvers += Resolver.sonatypeRepo("snapshots")
organization := "martin"
name := "value-transactions"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.3"

val Http4sVersion = "0.17.0-RC1"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  // web framework
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,

  // json
  "io.circe" %% "circe-generic" % "0.8.0",
  "io.circe" %% "circe-literal" % "0.8.0",
  "com.chuusai" %% "shapeless" % "2.3.2",
  "io.circe" %% "circe-core" % "0.8.0",
  compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),

  // tests
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  // parallelism
  "io.github.martintrojer" % "atom-scala_2.10" % "0.1-SNAPSHOT"
)

https://stackoverflow.com/a/28498443/2159808
// META-INF discarding
mergeStrategy in assembly <<= (mergeStrategy in assembly) {
  (old) => {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}
