package com.vc.server.routes


import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object Routes {
  def routes(implicit actorSystem: ActorSystem): Route = concat(
    HealthCheckRoutes.healthCheck, PageViewAggRoutes.pageViewAggRoutes
  )
}
