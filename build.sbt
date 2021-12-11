ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.0.2"
ThisBuild / organization := "io.darkgoat"

lazy val artifactoryUsername = sys.env("DARKGOAT_ARTIFACTORY_USERNAME")
lazy val artifactoryPassword = sys.env("DARKGOAT_ARTIFACTORY_PASSWORD")
lazy val timestamp           = new java.util.Date().getTime

ThisBuild / publishTo := Some(
  "Artifactory Realm".at("https://darkgoat.jfrog.io/artifactory/darkgoat-devel;build.timestamp=$timestamp")
)

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
