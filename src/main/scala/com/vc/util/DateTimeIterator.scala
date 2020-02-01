package com.vc.util

import org.joda.time.DateTime

case class DateTimeIterator(start: DateTime, end: DateTime) extends Iterator[DateTime] {
  val endInclusive = end.plusHours(1)
  private var current = start

  def next(): DateTime = if (hasNext) {
    val res = current;
    current = current.plusHours(1)
    res
  } else Iterator.empty.next

  def hasNext: Boolean = current.isBefore(endInclusive)
}
