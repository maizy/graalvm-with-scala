enablePlugins(JavaAppPackaging)

name := "scalacli"
organization := "space.maizy"

scalaVersion := "2.12.5"
libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.7.0"
)

scalacOptions ++= Seq(
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-deprecation",
  "-unchecked",
  "-explaintypes",
  "-Xfatal-warnings",
  "-Xlint"
)
