package com.vc.server.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, pathPrefix}
import akka.http.scaladsl.server.Route

object HealthCheckRoutes {
  //e.g. http://host:port/runok
  val healthCheck: Route = pathPrefix("ruok") {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "iamok"))
    }
  }
}
