package com.vc.util

import com.vc.model.PageViews
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class TopUtilTest extends FlatSpec with Matchers {

  "A TopUtilTest" should "return Map with sorted domain name" in {
    val p1 = "aa.b User:Sir_Lestaty_de_Lioncourt 1 0"
    val p2 = "aa User:Sir_Lestaty_de_Lioncourt 2 0"
    val p3 = "aa.c UaaaSir_Lestaty_de_Lioncourt 5 0"
    val p4 = "aa.b UaaaSir_Lestaty_de_Lioncourt 1 0"
    val p5 = "aa.b UaaaSir_Lestaty_de_Lioncourt 9 0"
    val p6 = "aa.b UaaaSr_Lestaty_de_Lioncourt 9 0"
    val p7 = "aa Usr:Sir_Lestaty_de_Lioncourt 9 0"
    val list = List(p1,p2,p3,p4,p5,p6,p7)

    //TopUtil Response
    val map = TopUtil.topMap(list.toIterator, Set(""), 3)

    //Expected Response
    val expectedMap = new mutable.TreeMap[String, mutable.PriorityQueue[PageViews]]()
    expectedMap.put("aa", mutable.PriorityQueue(PageViewFormatter.format(p2).get, PageViewFormatter.format(p7).get))
    expectedMap.put("aa.b", mutable.PriorityQueue(PageViewFormatter.format(p4).get, PageViewFormatter.format(p6).get, PageViewFormatter.format(p5).get))
    expectedMap.put("aa.c", mutable.PriorityQueue(PageViewFormatter.format(p3).get))

    assert(map.mkString == expectedMap.mkString)
  }

  it should "not return any domain & page which is blacklisted" in {
    val p1 = "aa.b User:Sir_Lestaty_de_Lioncourt 1 0"
    val p2 = "aa User:Sir_Lestaty_de_Lioncourt 2 0"
    val p3 = "aa.c UaaaSir_Lestaty_de_Lioncourt 5 0"
    val p4 = "aa.b UaaaSir_Lestaty_de_Lioncourt 1 0"
    val p5 = "aa.b UaaaSir_Lestaty_de_Lioncourt 9 0"
    val p6 = "aa.b UaaaSr_Lestaty_de_Lioncourt 9 0"
    val p7 = "aa Usr:Sir_Lestaty_de_Lioncourt 9 0"
    val list = List(p1,p2,p3,p4,p5,p6,p7)

    //TopUtil Response
    val map = TopUtil.topMap(list.toIterator, Set("aa Usr:Sir_Lestaty_de_Lioncourt"), 3)

    //Expected Response
    val expectedMap = new mutable.TreeMap[String, mutable.PriorityQueue[PageViews]]()
    expectedMap.put("aa", mutable.PriorityQueue(PageViewFormatter.format(p2).get))
    expectedMap.put("aa.b", mutable.PriorityQueue(PageViewFormatter.format(p4).get, PageViewFormatter.format(p6).get, PageViewFormatter.format(p5).get))
    expectedMap.put("aa.c", mutable.PriorityQueue(PageViewFormatter.format(p3).get))

    assert(map.mkString == expectedMap.mkString)
  }
}