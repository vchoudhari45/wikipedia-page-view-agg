package com.vc.util

import com.vc.model.PageViews
import org.scalatest.{FlatSpec, Matchers}

class PageViewFormatterTest extends FlatSpec with Matchers {

  "A PageViewFormatterTest" should "return Some(PageView)" in {
    val p1 = "aa.b User:Sir_Lestaty_de_Lioncourt 1 0"
    val response = PageViewFormatter.format(p1)
    val expected = Some(PageViews("aa.b", "User:Sir_Lestaty_de_Lioncourt", 1, 0))
    assert(response == expected)
  }

  it should "return empty if format is wrong" in {
    val p1 = "aa.b  User:Sir_Lestaty_de_Lioncourt 1 0"
    val response = PageViewFormatter.format(p1)
    assert(response.isEmpty)
  }
}
