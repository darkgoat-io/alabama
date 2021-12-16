ThisBuild / publishTo :=
  sys.env
    .get("ALABAMA_ARTIFACTORY_URL")
    .map(artifactoryUrl => "Artifactory Realm".at(artifactoryUrl))

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
