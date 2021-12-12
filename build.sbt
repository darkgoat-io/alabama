enablePlugins(GitVersioning, GitBranchPrompt)

ThisBuild / scalaVersion := "3.0.2"
ThisBuild / organization := "io.darkgoat"

lazy val artifactoryUrl = "https://darkgoat.jfrog.io/artifactory/darkgoat-release"
ThisBuild / publishTo := Some("Artifactory Realm".at(artifactoryUrl))
ThisBuild / credentials ++= (for {
  userName <- sys.env.get("DARKGOAT_ARTIFACTORY_USERNAME")
  passwd   <- sys.env.get("DARKGOAT_ARTIFACTORY_PASSWORD")
} yield Credentials(
  realm = "Artifactory Realm",
  host = "darkgoat.jfrog.io",
  userName = userName,
  passwd = passwd
)).toSeq

lazy val fs = (project in file("modules/fs"))
  .settings(
    name := "alabama-fs"
  )

lazy val alabama = (project in file("."))
  .aggregate(fs)
  .settings(
    name := "alabama"
  )
