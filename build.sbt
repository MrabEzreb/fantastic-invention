name := "Drazil"

scalaVersion := "2.11.8"

lazy val root = project.in(file(".")).aggregate(client, server, game)

lazy val commonDeps = Seq(
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.6",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test",
    "com.typesafe.akka" %% "akka-actor" % "2.4.10",
    "co.technius" %% "scalua" % "0.0.1-SNAPSHOT"
)

lazy val game = project
    .in(file("game"))
    .settings(
        EclipseKeys.useProjectId := true,
        EclipseKeys.withSource := true,
        EclipseKeys.withJavadoc := true,
        scalaVersion := "2.11.8",
        libraryDependencies ++= commonDeps,
        fork := true,
        resolvers += Resolver.sonatypeRepo("snapshots")
    )
lazy val client = project
    .in(file("client"))
    .dependsOn(game)
    .settings(
        EclipseKeys.useProjectId := true,
        EclipseKeys.withSource := true,
        EclipseKeys.withJavadoc := true,
        scalaVersion := "2.11.8",
        libraryDependencies ++= commonDeps,
        fork := true,
        resolvers += Resolver.sonatypeRepo("snapshots")
    )
lazy val server = project
    .in(file("server"))
    .dependsOn(game)
    .settings(
        EclipseKeys.useProjectId := true,
        EclipseKeys.withSource := true,
        EclipseKeys.withJavadoc := true,
        scalaVersion := "2.11.8",
        libraryDependencies ++= commonDeps,
        fork := true,
        resolvers += Resolver.sonatypeRepo("snapshots")
    )
fork := true