package com.vc.controller

import java.io.{File, FileOutputStream, PrintWriter}
import java.util.zip.GZIPInputStream

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.vc.config.Config
import com.vc.util.{BlackList, ConnectionUtil, TopUtil, UrlGenerator}

import scala.io.Codec
import scala.util.{Failure, Success}

object PageViewAggController {
  def run(from: Option[String], to: Option[String])(implicit actorSystem: ActorSystem): Route = {
    BlackList.blackList match {
      case Success(blackList) => {
        val requests = UrlGenerator.urlGenerator(from, to)
        requests.foreach(url => {
          val fileToBeCreated = new File(s"${url._2}")

          //Only create output file if it doesn't exists
          if (!fileToBeCreated.exists()) {
            val conn = ConnectionUtil.getConnection(url._1)
            conn.connect()

            //Download the file and read it as Input Stream
            val gZip = new GZIPInputStream(conn.getInputStream, 64 * 1024)
            val lines = scala.io.Source.fromInputStream(gZip)(Codec.UTF8).getLines()

            val pw = new PrintWriter(new FileOutputStream(s"${url._2}"))

            //Generate TreeMap with TopK elements in PriorityQueue as value
            val map = TopUtil.topMap(lines, blackList, Config.top)

            //Write the Priority Queue to File
            map.foreach(pageView => pageView._2.reverse.foreach(pw.println))

            //Flush and close the connections
            pw.flush()
            pw.close()
            conn.disconnect()
          }
        })
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h3>File created on your local, Please check your project directory...<h3>"))
      }
      case Failure(exception) => failWith(exception)
    }
  }
}

