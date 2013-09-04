import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "londonLettingsWizard"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
    "net.sf.flexjson" % "flexjson" % "2.0"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    testOptions in Test ~= { args =>
      for {
        arg <- args
        val ta: Tests.Argument = arg.asInstanceOf[Tests.Argument]
        val newArg = if(ta.framework == Some(TestFrameworks.JUnit)) ta.copy(args = List.empty[String]) else ta
      } yield newArg
    }
  )

}
