package com.vc.util

import com.vc.model.PageViews

import scala.collection.mutable

object TopUtil {
  def topMap(lines: Iterator[String], blackList: Set[String], top: Int): mutable.TreeMap[String, mutable.PriorityQueue[PageViews]] = {
    val map = new mutable.TreeMap[String, mutable.PriorityQueue[PageViews]]()
    lines.foreach(line => {
      val pageView = PageViewFormatter.format(line)
      pageView match {
        case Some(pv) => if (!blackList.contains(pv.key)) {
          if (map.contains(pv.domain)) {
            val pq = map.getOrElse(pv.domain, new mutable.PriorityQueue[PageViews]())
            pq.enqueue(pv)

            //We only want top element
            if (pq.size > top) pq.dequeue
          }
          else {
            val pq = new mutable.PriorityQueue[PageViews]()
            pq += pv
            map.put(pv.domain, pq)
          }
        }
        case None =>
      }
    })
    map
  }
}
