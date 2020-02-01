package com.vc.model

case class PageViews(domain: String, page: String, viewCount: Long, responseSize: Long) extends Ordered[PageViews] {
  val key = domain + " " + page

  override def compare(that: PageViews): Int = {
    if (this.viewCount < that.viewCount) 1
    else if (this.viewCount > that.viewCount) -1
    else {
      if (this.key > that.key) 1
      else if (this.key < that.key) -1
      else 0
    }
  }

  override def toString(): String = domain + " " + page + " " + viewCount + " " + responseSize
}
