enablePlugins(GitVersioning, GitBranchPrompt)

ThisBuild / scalaVersion := "3.0.2"
ThisBuild / organization := "io.darkgoat"

lazy val artifactoryUsername = sys.env("DARKGOAT_ARTIFACTORY_USERNAME")
lazy val artifactoryPassword = sys.env("DARKGOAT_ARTIFACTORY_PASSWORD")
lazy val artifactoryUrl      = "https://darkgoat.jfrog.io/artifactory/darkgoat-release"

ThisBuild / publishTo := Some("Artifactory Realm".at(artifactoryUrl))
ThisBuild / credentials += Credentials(
  realm = "Artifactory Realm",
  host = "darkgoat.jfrog.io",
  userName = artifactoryUsername,
  passwd = artifactoryPassword
)

lazy val fs = (project in file("modules/fs"))
  .settings(
    name := "alabama-fs"
  )

lazy val alabama = (project in file("."))
  .aggregate(fs)
  .settings(
    name := "alabama"
  )
