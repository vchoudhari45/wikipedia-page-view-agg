package com.vc.util

import com.vc.config.Config
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object UrlGenerator {
  def urlGenerator(fromDate: Option[String], toDate: Option[String]): List[(String, String)] = {
    val fmt = DateTimeFormat.forPattern("yyyyMMddHH")
    val start = fromDate.fold(new DateTime())(fmt.parseDateTime)
    val end = toDate.fold(start)(fmt.parseDateTime)
    val uri = Config.pageView
    (for {
      dateTime <- DateTimeIterator(start, end)
    } yield {
      val yyyy = dateTime.getYear.toString
      val mm = "%02d".format(dateTime.getMonthOfYear)
      val dd = "%02d".format(dateTime.getDayOfMonth)
      val hh = "%02d".format(dateTime.getHourOfDay)
      val replaced = uri.replaceAll("#YYYY#", yyyy).replaceAll("#MM#", mm).replaceAll("#DD#", dd).replaceAll("#HH#", hh)
      (replaced, yyyy + mm + dd + hh + ".txt")
    }).toList
  }
}
