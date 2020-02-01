package com.vc.server.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{get, parameters, path, _}
import akka.http.scaladsl.server.Route
import com.vc.controller.PageViewAggController

object PageViewAggRoutes {
  def pageViewAggRoutes(implicit actorSystem: ActorSystem): Route = path("page-view") {
    //e.g. http://host:port/page-view?from=yyyymmddhh&to=yyyymmddhh
    get {
      parameters('from.?, 'to.?, 'save ? true) {
        //(from, to, save) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"from: $from to: $to save: $save"))
        (from, to, save) => PageViewAggController.run(from, to)
      }
    }
  }
}
