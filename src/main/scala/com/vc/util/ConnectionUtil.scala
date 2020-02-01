package com.vc.util

import java.net.{HttpURLConnection, URL}

object ConnectionUtil {
  def getConnection(urlStr: String): HttpURLConnection = {
    val url = new URL(urlStr)
    val conn = url.openConnection.asInstanceOf[HttpURLConnection]
    conn
  }
}
