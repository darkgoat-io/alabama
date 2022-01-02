enablePlugins(GitVersioning, GitBranchPrompt)

import sbt.Keys.isSnapshot

ThisBuild / git.useGitDescribe.withRank(KeyRanks.Invisible) := true

val sanitizedGitCurrentBranch = Def.setting {
  git.gitCurrentBranch.value.split('/').mkString("-")
}

val releaseTagRegex  = raw"v(\d+.\d+.\d+)".r
val developmentRegex = raw"v(\d+.\d+.\d+-\d+-g[0-9a-f]{7})".r
ThisBuild / git.gitTagToVersionNumber := {
  case releaseTagRegex(tag)  => Some(tag)
  case developmentRegex(tag) => Some(s"$tag-${sanitizedGitCurrentBranch.value}")
  case tag                   => Some(s"unknown-$tag-${sanitizedGitCurrentBranch.value}")
}

ThisBuild / isSnapshot := {
  val release = raw"(\d+.\d+.\d+)".r
  version.value match {
    case release(_) => false
    case _          => true
  }
}
