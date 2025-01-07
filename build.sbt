name := """Backend"""
organization := "com.example"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  "mysql" % "mysql-connector-java" % "8.0.22",
//  "io.ebean" % "ebean" % "12.16.4",
  "com.h2database" % "h2" % "1.4.192",
  "com.auth0" % "java-jwt" % "3.18.2"
  // JWT library for Play
)

//libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.11"

resolvers += "Typesafe Repo" at "https://repo.typesafe.com/typesafe/releases/"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.11"
libraryDependencies += "com.typesafe.play" %% "play" % play.core.PlayVersion.current
//libraryDependencies += "com.typesafe.play" %% "play-filters-cors" % "0.1.0"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"
libraryDependencies += "com.typesafe.play" %% "filters-helpers" % "2.4.0"

libraryDependencies += "com.smartcoin" %% "payvoo-common-utils" % "1.1.9"
resolvers += BitbucketResolver.url("https://api.bitbucket.org/2.0/repositories/financetech/jars/src/master")




javacOptions ++= Seq("-source", "1.8", "-target", "1.8")