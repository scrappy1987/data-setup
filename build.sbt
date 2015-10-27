import Lib._

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(Settings.basicSettings: _*)
  .settings(Settings.serviceSettings: _*)
  .settings(libraryDependencies ++= Seq(
    javaJpa, hibernate, cache, javaWs, evolutions
  ) ++ Lib.test(
    junit
  ))

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

fork in run := true
