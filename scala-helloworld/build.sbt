enablePlugins(JavaAppPackaging)

name := "scalahw"
organization := "space.maizy"

scalaVersion := "2.12.5"

scalacOptions ++= Seq(
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-deprecation",
  "-unchecked",
  "-explaintypes",
  "-Xfatal-warnings",
  "-Xlint"
)
