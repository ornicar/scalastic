import sbt._
import sbt.Keys._
import scala._
import scala.Some

object Build extends Build {

  val basicSettings = Seq(
    organization := "org.scalastic",
    name := "scalastic",
    version := "0.90.5-THIB",
    description := "a scala driver for elasticsearch",
    homepage := Some(url("https://github.com/bsadeh/scalastic")),
    licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
  )

  val scalaSettings = Seq(
    scalaVersion := "2.11.0-M4",
    scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation")
  )

  val publishSettings = Seq(
    publishMavenStyle := true,
    publishArtifact in Compile := true,
    publishArtifact in Test := false,
    publishTo := Some(Resolver.sftp(
      "iliaz",
      "scala.iliaz.com"
    ) as ("scala_iliaz_com", Path.userHome / ".ssh" / "id_rsa")),

    pomIncludeRepository := { _ => false },

    pomExtra :=
      <scm>
        <url>scm:git:git@github.com:bsadeh/scalastic.git</url>
        <connection>scm:git:git@github.com:bsadeh/scalastic.git</connection>
        <developerConnection>scm:git:git@github.com:bsadeh/scalastic.git</developerConnection>
      </scm>

      <developers>
        <developer>
          <id>bsadeh</id>
          <name>Benny Sadeh</name>
        </developer>
        <developer>
          <id>yatskevich</id>
          <name>Ivan Yatskevich</name>
        </developer>
      </developers>

      <issueManagement>
        <system>GitHub</system>
        <url>https://github.com/bsadeh/scalastic/issues/</url>
      </issueManagement>
  )

  lazy val Root = Project(id = "Root", base = file("."))
    .settings(basicSettings: _*)
    .settings(scalaSettings: _*)
    .settings(publishSettings: _*)
    .settings(
    resolvers += Resolver.sonatypeRepo("releases"),

    libraryDependencies ++= Seq(
      "org.elasticsearch" % "elasticsearch" % "0.90.5",

      "org.clapper" % "grizzled-slf4j_2.10" % "1.0.1",

      "junit" % "junit" % "4.10" % "test",
      "org.scalatest" % "scalatest_2.10" % "1.9.2" % "test",
      "ch.qos.logback" % "logback-classic" % "1.0.13" % "test"
    ),

    parallelExecution in Test := false
  )

}
