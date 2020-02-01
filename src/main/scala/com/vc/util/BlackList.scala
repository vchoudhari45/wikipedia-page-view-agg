package com.vc.util

import java.io.{File, PrintWriter}
import java.nio.charset.StandardCharsets

import com.vc.config.Config

import scala.io.Codec
import scala.util.Try

object BlackList {
  lazy val blackList = Try {
    val resources = getClass.getResource("/")
    val blacklistFile = new File(resources.getPath + "blackList.txt")

    //if blacklisted domain file doesn't exist on local download it
    if (!blacklistFile.exists()) {
      val bConn = ConnectionUtil.getConnection(Config.blackList)
      bConn.connect()
      val lines = scala.io.Source.fromInputStream(bConn.getInputStream)(Codec.UTF8).getLines()
      val pw = new PrintWriter(blacklistFile)
      lines.foreach(pw.write)
      pw.flush()
      pw.close()
      bConn.disconnect()
    }

    //Read the file from local and convert it to set
    val inputFile = scala.io.Source.fromFile(blacklistFile)
    val lines = inputFile.getLines()
    val blackList = (for {line <- lines} yield {
      if (line.lastIndexOf("%") != -1) java.net.URLDecoder.decode(line.substring(0, line.lastIndexOf("%")).replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B"), StandardCharsets.UTF_8.name)
      else java.net.URLDecoder.decode(line.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B"), StandardCharsets.UTF_8.name)
    }).toSet

    inputFile.close()
    blackList
  }
}
