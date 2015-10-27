import play.sbt.routes.RoutesKeys._
import sbt.Keys._

object Settings {
  lazy val basicSettings = Seq(
    version := Version.APP,
    scalaVersion := Version.SCALA,
    scalaBinaryVersion := "2.11",
    autoScalaLibrary := false,
    resolvers ++= Lib.repos,
    scalacOptions := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.8",
      "-language:_",
      "-Xlog-reflective-calls",
      "-Ylog-classpath"
    )
  )

  lazy val serviceSettings = Seq(
    // Play provides two styles of routers, one expects its actions to be injected, the
    // other, legacy style, accesses its actions statically.
    routesGenerator := InjectedRoutesGenerator
    //PlayKeys.externalizeResources := false
  )
}
