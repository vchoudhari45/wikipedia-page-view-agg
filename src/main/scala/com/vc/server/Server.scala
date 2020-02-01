package com.vc.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.vc.config.Config
import com.vc.server.routes.Routes

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Server {
  def main(args: Array[String]): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
    val bindingFuture = Http().bindAndHandle(Routes.routes, Config.host, Config.port)

    println(s"Server online at http://${Config.host}:${Config.port}")
    println("Press RETURN to stop...")
    StdIn.readLine() // let it run until user presses return

    //shutdown
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => actorSystem.terminate()) // and shutdown when done
  }
}
