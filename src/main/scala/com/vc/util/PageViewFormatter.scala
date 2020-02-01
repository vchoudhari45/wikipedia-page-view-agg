package com.vc.util

import com.vc.model.PageViews

import scala.util.Try

object PageViewFormatter {
  def format(line: String): Option[PageViews] = {
    Try {
      val s1 = line.indexOf(' ', 0)
      val s2 = line.indexOf(' ', s1 + 1)
      val s3 = line.indexOf(' ', s2 + 1)
      PageViews(
        line.substring(0, s1),
        line.substring(s1 + 1, s2),
        line.substring(s2 + 1, s3).toLong,
        line.substring(s3 + 1).toLong
      )
    }.toOption
  }
}
