package space.maizy.scalacli

/**
 * Copyright (c) Nikita Kovaliov, maizy.ru, 2018
 * See LICENSE.txt for details.
 */

import java.nio.file.{ Path, Paths }

object ScalaCliApp {

  def main(args: Array[String]) {

    val BAD_ARGUMENTS_ERROR = 2

    case class AppOptions(basePath: Path = Paths.get("./"), cmd: Option[String] = None)

    object OptionParser {
      private val parser = new scopt.OptionParser[AppOptions]("java -jar scalacli.jar") {
        help("help")
        (cmd("gen")
          action { (_, opts) => opts.copy(cmd = Some("gen")) }
          text { "generate site" }
          children(
            opt[String]('o', "output")
              text { "base output path, will be created if not exist" }
              required()
              action { (value, opts) =>
                val path = Paths.get(value).toAbsolutePath.normalize
                opts.copy(basePath = path)
            }
          )
        )
      }

      def parse(args: Seq[String]): Option[AppOptions] = {
        val opts = parser.parse(args, AppOptions())
        if (opts.isEmpty || opts.get.cmd.isEmpty) {
          parser.showUsageAsError
          None
        } else {
          opts
        }
      }
    }

    val mayBeOpts = OptionParser.parse(args)
    if (mayBeOpts.isEmpty) {
      System.exit(BAD_ARGUMENTS_ERROR)
    }
    val opts = mayBeOpts.get
    println(s"Opts: $opts")
  }
}
