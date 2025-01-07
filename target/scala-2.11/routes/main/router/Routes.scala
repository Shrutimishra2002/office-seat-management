
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shruti_sm1540_sc418/Downloads/Template/conf/routes
// @DATE:Tue Jan 07 12:32:23 IST 2025

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:19
  UserController_0: controllers.UserController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:19
    UserController_0: controllers.UserController
  ) = this(errorHandler, UserController_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, UserController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signup""", """controllers.UserController.signUp"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.UserController.login"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home""", """controllers.UserController.home"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """testConnection""", """controllers.UserController.testConnection"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:19
  private[this] lazy val controllers_UserController_signUp0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup")))
  )
  private[this] lazy val controllers_UserController_signUp0_invoker = createInvoker(
    UserController_0.signUp,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "signUp",
      Nil,
      "POST",
      """ UserController routes""",
      this.prefix + """signup"""
    )
  )

  // @LINE:20
  private[this] lazy val controllers_UserController_login1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_UserController_login1_invoker = createInvoker(
    UserController_0.login,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "login",
      Nil,
      "POST",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:21
  private[this] lazy val controllers_UserController_home2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home")))
  )
  private[this] lazy val controllers_UserController_home2_invoker = createInvoker(
    UserController_0.home,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "home",
      Nil,
      "GET",
      """""",
      this.prefix + """home"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_UserController_testConnection3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("testConnection")))
  )
  private[this] lazy val controllers_UserController_testConnection3_invoker = createInvoker(
    UserController_0.testConnection,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "testConnection",
      Nil,
      "GET",
      """""",
      this.prefix + """testConnection"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:19
    case controllers_UserController_signUp0_route(params) =>
      call { 
        controllers_UserController_signUp0_invoker.call(UserController_0.signUp)
      }
  
    // @LINE:20
    case controllers_UserController_login1_route(params) =>
      call { 
        controllers_UserController_login1_invoker.call(UserController_0.login)
      }
  
    // @LINE:21
    case controllers_UserController_home2_route(params) =>
      call { 
        controllers_UserController_home2_invoker.call(UserController_0.home)
      }
  
    // @LINE:22
    case controllers_UserController_testConnection3_route(params) =>
      call { 
        controllers_UserController_testConnection3_invoker.call(UserController_0.testConnection)
      }
  }
}
