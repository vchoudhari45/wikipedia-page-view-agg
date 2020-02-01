package com.vc.config

import com.typesafe.config.ConfigFactory

object Config {
  lazy val host: String = config.getString("server.host")
  lazy val port: Int = config.getInt("server.port")
  lazy val blackList: String = config.getString("uris.blackList")
  lazy val pageView: String = config.getString("uris.pageView")
  lazy val top: Int = config.getInt("top")
  private lazy val config = ConfigFactory.load
}
