enablePlugins(GitVersioning, GitBranchPrompt)

ThisBuild / scalaVersion := "3.0.2"
ThisBuild / organization := "io.darkgoat"

lazy val fs = (project in file("modules/fs"))
  .settings(
    name := "alabama-fs"
  )

lazy val alabama = (project in file("."))
  .aggregate(fs)
  .settings(
    name := "alabama"
  )
