ThisBuild / publishTo := {
  val repo = "https://darkgoat.jfrog.io/artifactory"
  if (isSnapshot.value) Some("Artifactory Realm".at(s"$repo/snapshots"))
  else Some("Artifactory Realm".at(s"$repo/releases"))
}

ThisBuild / credentials ++=
  (for {
    userName <- sys.env.get("ALABAMA_ARTIFACTORY_USERNAME")
    passwd   <- sys.env.get("ALABAMA_ARTIFACTORY_PASSWORD")
  } yield Credentials(
    realm = "Artifactory Realm",
    host = "darkgoat.jfrog.io",
    userName = userName,
    passwd = passwd
  )).toSeq
