import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "sunerp"
  val appVersion = "0.0.1"

  val templateEngine = Seq(
    "com.mohiva" %% "play-html-compressor" % "0.2-SNAPSHOT",
    "org.ocpsoft.prettytime" % "prettytime" % "3.2.4.Final",
    "com.scalatags" %% "scalatags" % "0.2.2"
  )

  val persistentDependencies = Seq(
    jdbc,
    "joda-time" % "joda-time" % "2.3",
    "org.joda" % "joda-convert" % "1.5",
    "com.github.tototoshi" %% "slick-joda-mapper" % "1.0.1" exclude("com.typesafe.slick", "slick"),
    "com.typesafe.slick" %% "slick" % "2.0.1-RC1",
    "com.typesafe.play" %% "play-slick" % "0.6.0.1" exclude("com.typesafe.slick", "slick"),
    "mysql" % "mysql-connector-java" % "5.1.25",
    "com.github.nscala-time" %% "nscala-time" % "0.8.0"
  )

  val appDependencies = Seq(
    "com.chuusai" % "shapeless" % "2.0.0-M1" cross CrossVersion.full,
    "org.scalaz" %% "scalaz-core" % "7.0.5",
    "commons-collections" % "commons-collections" % "3.2.1",
    "org.apache.commons" % "commons-lang3" % "3.1",
    "commons-digester" % "commons-digester" % "2.1" exclude("commons-beanutils", "commons-beanutils"),
    "commons-io" % "commons-io" % "2.4",
    "jp.t2v" %% "stackable-controller" % "0.3.0",
    "jp.t2v" %% "play2-auth" % "0.11.0",
    "jp.t2v" %% "play2-auth-test" % "0.11.0" % "test",
    "org.apache.commons" % "commons-email" % "1.3.1",
    "com.restfb" % "restfb" % "1.6.11",
    "com.google.http-client" % "google-http-client-jackson2" % "1.11.0-beta",
    "com.google.apis" % "google-api-services-oauth2" % "v2-rev48-1.16.0-rc",
    "org.mindrot" % "jbcrypt" % "0.3m",
    "org.scalautils" %% "scalautils" % "2.0"
  ) ++ persistentDependencies ++ templateEngine

  val appResolvers = Seq(
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
    "t2v.jp repo" at "http://www.t2v.jp/maven-repo/"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    templatesImport ++= Seq(
      "models.sunerp._",
      "models.qlkh._",
      "dtos._"
    ),
    resolvers ++= appResolvers
  )
}
