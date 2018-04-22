package space.maizy.scalahw

/**
 * Copyright (c) Nikita Kovaliov, maizy.ru, 2018
 * See LICENSE.txt for details.
 */

object ScalaHWApp {
  def main(args: Array[String]) {
    val name = "World"
    println(s"Hello $name: ${args.mkString(", ")}")
  }
}
