
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shruti_sm1540_sc418/Documents/OfficeSeatManagement/conf/routes
// @DATE:Tue Feb 04 11:37:14 IST 2025

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
  UserController_3: controllers.UserController,
  // @LINE:26
  SeatController_4: controllers.SeatController,
  // @LINE:33
  AdminController_0: controllers.AdminController,
  // @LINE:39
  OfficeController_1: controllers.OfficeController,
  // @LINE:47
  UserSeatAssignmentController_2: controllers.UserSeatAssignmentController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:19
    UserController_3: controllers.UserController,
    // @LINE:26
    SeatController_4: controllers.SeatController,
    // @LINE:33
    AdminController_0: controllers.AdminController,
    // @LINE:39
    OfficeController_1: controllers.OfficeController,
    // @LINE:47
    UserSeatAssignmentController_2: controllers.UserSeatAssignmentController
  ) = this(errorHandler, UserController_3, SeatController_4, AdminController_0, OfficeController_1, UserSeatAssignmentController_2, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, UserController_3, SeatController_4, AdminController_0, OfficeController_1, UserSeatAssignmentController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/signup""", """controllers.UserController.signUp"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/login""", """controllers.UserController.login"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home""", """controllers.UserController.home"""),
    ("""GET""", this.prefix, """controllers.UserController.testConnection"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listUsersNotAdmin""", """controllers.UserController.listUsersNotAdmin"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """office/""" + "$" + """officeId<[^/]+>/seats/add""", """controllers.SeatController.addSeat(officeId:Long)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateseats/""" + "$" + """id<[^/]+>""", """controllers.SeatController.updateSeat(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """seats/""" + "$" + """id<[^/]+>""", """controllers.SeatController.deleteSeat(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """office/""" + "$" + """officeId<[^/]+>/seats""", """controllers.SeatController.getSeats(officeId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """seatsfromoffice/""" + "$" + """officeId<[^/]+>""", """controllers.SeatController.allSeatsFromaSingleOffice(officeId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """admin/seat-utilization""", """controllers.AdminController.getSeatUtilizationReport()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """admin/seats""", """controllers.AdminController.addSeat()"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """admin/seats/""" + "$" + """id<[^/]+>""", """controllers.AdminController.removeSeat(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """office/create""", """controllers.OfficeController.createOffice()"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """office/delete""" + "$" + """id<[^/]+>""", """controllers.OfficeController.deleteOffice(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """offices/list""", """controllers.OfficeController.getOffices"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """office/""" + "$" + """id<[^/]+>""", """controllers.OfficeController.getOfficeById(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assignSeatToUser/""" + "$" + """userId<[^/]+>/""" + "$" + """seatId<[^/]+>""", """controllers.UserSeatAssignmentController.assignSeatToUser(userId:Long, seatId:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """removeSeatAssignment/""" + "$" + """userId<[^/]+>""", """controllers.UserSeatAssignmentController.removeSeatAssignment(userId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getAssignedSeat/""" + "$" + """userId<[^/]+>""", """controllers.UserSeatAssignmentController.getSeatAssignment(userId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """checkUserExists/""" + "$" + """userId<[^/]+>""", """controllers.UserSeatAssignmentController.checkSeatAssignment(userId:Long)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:19
  private[this] lazy val controllers_UserController_signUp0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/signup")))
  )
  private[this] lazy val controllers_UserController_signUp0_invoker = createInvoker(
    UserController_3.signUp,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "signUp",
      Nil,
      "POST",
      """ UserController routes""",
      this.prefix + """users/signup"""
    )
  )

  // @LINE:20
  private[this] lazy val controllers_UserController_login1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/login")))
  )
  private[this] lazy val controllers_UserController_login1_invoker = createInvoker(
    UserController_3.login,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "login",
      Nil,
      "POST",
      """""",
      this.prefix + """users/login"""
    )
  )

  // @LINE:21
  private[this] lazy val controllers_UserController_home2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home")))
  )
  private[this] lazy val controllers_UserController_home2_invoker = createInvoker(
    UserController_3.home,
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
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_UserController_testConnection3_invoker = createInvoker(
    UserController_3.testConnection,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "testConnection",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:23
  private[this] lazy val controllers_UserController_listUsersNotAdmin4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listUsersNotAdmin")))
  )
  private[this] lazy val controllers_UserController_listUsersNotAdmin4_invoker = createInvoker(
    UserController_3.listUsersNotAdmin,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "listUsersNotAdmin",
      Nil,
      "GET",
      """""",
      this.prefix + """listUsersNotAdmin"""
    )
  )

  // @LINE:26
  private[this] lazy val controllers_SeatController_addSeat5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("office/"), DynamicPart("officeId", """[^/]+""",true), StaticPart("/seats/add")))
  )
  private[this] lazy val controllers_SeatController_addSeat5_invoker = createInvoker(
    SeatController_4.addSeat(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SeatController",
      "addSeat",
      Seq(classOf[Long]),
      "POST",
      """ Seat Routes""",
      this.prefix + """office/""" + "$" + """officeId<[^/]+>/seats/add"""
    )
  )

  // @LINE:27
  private[this] lazy val controllers_SeatController_updateSeat6_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateseats/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SeatController_updateSeat6_invoker = createInvoker(
    SeatController_4.updateSeat(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SeatController",
      "updateSeat",
      Seq(classOf[Long]),
      "PUT",
      """""",
      this.prefix + """updateseats/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:28
  private[this] lazy val controllers_SeatController_deleteSeat7_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("seats/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SeatController_deleteSeat7_invoker = createInvoker(
    SeatController_4.deleteSeat(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SeatController",
      "deleteSeat",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """seats/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:29
  private[this] lazy val controllers_SeatController_getSeats8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("office/"), DynamicPart("officeId", """[^/]+""",true), StaticPart("/seats")))
  )
  private[this] lazy val controllers_SeatController_getSeats8_invoker = createInvoker(
    SeatController_4.getSeats(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SeatController",
      "getSeats",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """office/""" + "$" + """officeId<[^/]+>/seats"""
    )
  )

  // @LINE:30
  private[this] lazy val controllers_SeatController_allSeatsFromaSingleOffice9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("seatsfromoffice/"), DynamicPart("officeId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SeatController_allSeatsFromaSingleOffice9_invoker = createInvoker(
    SeatController_4.allSeatsFromaSingleOffice(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SeatController",
      "allSeatsFromaSingleOffice",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """seatsfromoffice/""" + "$" + """officeId<[^/]+>"""
    )
  )

  // @LINE:33
  private[this] lazy val controllers_AdminController_getSeatUtilizationReport10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("admin/seat-utilization")))
  )
  private[this] lazy val controllers_AdminController_getSeatUtilizationReport10_invoker = createInvoker(
    AdminController_0.getSeatUtilizationReport(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AdminController",
      "getSeatUtilizationReport",
      Nil,
      "GET",
      """ Admin routes""",
      this.prefix + """admin/seat-utilization"""
    )
  )

  // @LINE:34
  private[this] lazy val controllers_AdminController_addSeat11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("admin/seats")))
  )
  private[this] lazy val controllers_AdminController_addSeat11_invoker = createInvoker(
    AdminController_0.addSeat(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AdminController",
      "addSeat",
      Nil,
      "POST",
      """""",
      this.prefix + """admin/seats"""
    )
  )

  // @LINE:35
  private[this] lazy val controllers_AdminController_removeSeat12_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("admin/seats/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AdminController_removeSeat12_invoker = createInvoker(
    AdminController_0.removeSeat(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AdminController",
      "removeSeat",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """admin/seats/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:39
  private[this] lazy val controllers_OfficeController_createOffice13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("office/create")))
  )
  private[this] lazy val controllers_OfficeController_createOffice13_invoker = createInvoker(
    OfficeController_1.createOffice(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OfficeController",
      "createOffice",
      Nil,
      "POST",
      """ Create a new office""",
      this.prefix + """office/create"""
    )
  )

  // @LINE:40
  private[this] lazy val controllers_OfficeController_deleteOffice14_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("office/delete"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_OfficeController_deleteOffice14_invoker = createInvoker(
    OfficeController_1.deleteOffice(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OfficeController",
      "deleteOffice",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """office/delete""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:42
  private[this] lazy val controllers_OfficeController_getOffices15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("offices/list")))
  )
  private[this] lazy val controllers_OfficeController_getOffices15_invoker = createInvoker(
    OfficeController_1.getOffices,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OfficeController",
      "getOffices",
      Nil,
      "GET",
      """""",
      this.prefix + """offices/list"""
    )
  )

  // @LINE:43
  private[this] lazy val controllers_OfficeController_getOfficeById16_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("office/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_OfficeController_getOfficeById16_invoker = createInvoker(
    OfficeController_1.getOfficeById(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OfficeController",
      "getOfficeById",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """office/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:47
  private[this] lazy val controllers_UserSeatAssignmentController_assignSeatToUser17_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assignSeatToUser/"), DynamicPart("userId", """[^/]+""",true), StaticPart("/"), DynamicPart("seatId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserSeatAssignmentController_assignSeatToUser17_invoker = createInvoker(
    UserSeatAssignmentController_2.assignSeatToUser(fakeValue[Long], fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserSeatAssignmentController",
      "assignSeatToUser",
      Seq(classOf[Long], classOf[Long]),
      "POST",
      """ conf/routes""",
      this.prefix + """assignSeatToUser/""" + "$" + """userId<[^/]+>/""" + "$" + """seatId<[^/]+>"""
    )
  )

  // @LINE:48
  private[this] lazy val controllers_UserSeatAssignmentController_removeSeatAssignment18_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("removeSeatAssignment/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserSeatAssignmentController_removeSeatAssignment18_invoker = createInvoker(
    UserSeatAssignmentController_2.removeSeatAssignment(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserSeatAssignmentController",
      "removeSeatAssignment",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """removeSeatAssignment/""" + "$" + """userId<[^/]+>"""
    )
  )

  // @LINE:49
  private[this] lazy val controllers_UserSeatAssignmentController_getSeatAssignment19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getAssignedSeat/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserSeatAssignmentController_getSeatAssignment19_invoker = createInvoker(
    UserSeatAssignmentController_2.getSeatAssignment(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserSeatAssignmentController",
      "getSeatAssignment",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """getAssignedSeat/""" + "$" + """userId<[^/]+>"""
    )
  )

  // @LINE:50
  private[this] lazy val controllers_UserSeatAssignmentController_checkSeatAssignment20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("checkUserExists/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserSeatAssignmentController_checkSeatAssignment20_invoker = createInvoker(
    UserSeatAssignmentController_2.checkSeatAssignment(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserSeatAssignmentController",
      "checkSeatAssignment",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """checkUserExists/""" + "$" + """userId<[^/]+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:19
    case controllers_UserController_signUp0_route(params) =>
      call { 
        controllers_UserController_signUp0_invoker.call(UserController_3.signUp)
      }
  
    // @LINE:20
    case controllers_UserController_login1_route(params) =>
      call { 
        controllers_UserController_login1_invoker.call(UserController_3.login)
      }
  
    // @LINE:21
    case controllers_UserController_home2_route(params) =>
      call { 
        controllers_UserController_home2_invoker.call(UserController_3.home)
      }
  
    // @LINE:22
    case controllers_UserController_testConnection3_route(params) =>
      call { 
        controllers_UserController_testConnection3_invoker.call(UserController_3.testConnection)
      }
  
    // @LINE:23
    case controllers_UserController_listUsersNotAdmin4_route(params) =>
      call { 
        controllers_UserController_listUsersNotAdmin4_invoker.call(UserController_3.listUsersNotAdmin)
      }
  
    // @LINE:26
    case controllers_SeatController_addSeat5_route(params) =>
      call(params.fromPath[Long]("officeId", None)) { (officeId) =>
        controllers_SeatController_addSeat5_invoker.call(SeatController_4.addSeat(officeId))
      }
  
    // @LINE:27
    case controllers_SeatController_updateSeat6_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_SeatController_updateSeat6_invoker.call(SeatController_4.updateSeat(id))
      }
  
    // @LINE:28
    case controllers_SeatController_deleteSeat7_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_SeatController_deleteSeat7_invoker.call(SeatController_4.deleteSeat(id))
      }
  
    // @LINE:29
    case controllers_SeatController_getSeats8_route(params) =>
      call(params.fromPath[Long]("officeId", None)) { (officeId) =>
        controllers_SeatController_getSeats8_invoker.call(SeatController_4.getSeats(officeId))
      }
  
    // @LINE:30
    case controllers_SeatController_allSeatsFromaSingleOffice9_route(params) =>
      call(params.fromPath[Long]("officeId", None)) { (officeId) =>
        controllers_SeatController_allSeatsFromaSingleOffice9_invoker.call(SeatController_4.allSeatsFromaSingleOffice(officeId))
      }
  
    // @LINE:33
    case controllers_AdminController_getSeatUtilizationReport10_route(params) =>
      call { 
        controllers_AdminController_getSeatUtilizationReport10_invoker.call(AdminController_0.getSeatUtilizationReport())
      }
  
    // @LINE:34
    case controllers_AdminController_addSeat11_route(params) =>
      call { 
        controllers_AdminController_addSeat11_invoker.call(AdminController_0.addSeat())
      }
  
    // @LINE:35
    case controllers_AdminController_removeSeat12_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_AdminController_removeSeat12_invoker.call(AdminController_0.removeSeat(id))
      }
  
    // @LINE:39
    case controllers_OfficeController_createOffice13_route(params) =>
      call { 
        controllers_OfficeController_createOffice13_invoker.call(OfficeController_1.createOffice())
      }
  
    // @LINE:40
    case controllers_OfficeController_deleteOffice14_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_OfficeController_deleteOffice14_invoker.call(OfficeController_1.deleteOffice(id))
      }
  
    // @LINE:42
    case controllers_OfficeController_getOffices15_route(params) =>
      call { 
        controllers_OfficeController_getOffices15_invoker.call(OfficeController_1.getOffices)
      }
  
    // @LINE:43
    case controllers_OfficeController_getOfficeById16_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_OfficeController_getOfficeById16_invoker.call(OfficeController_1.getOfficeById(id))
      }
  
    // @LINE:47
    case controllers_UserSeatAssignmentController_assignSeatToUser17_route(params) =>
      call(params.fromPath[Long]("userId", None), params.fromPath[Long]("seatId", None)) { (userId, seatId) =>
        controllers_UserSeatAssignmentController_assignSeatToUser17_invoker.call(UserSeatAssignmentController_2.assignSeatToUser(userId, seatId))
      }
  
    // @LINE:48
    case controllers_UserSeatAssignmentController_removeSeatAssignment18_route(params) =>
      call(params.fromPath[Long]("userId", None)) { (userId) =>
        controllers_UserSeatAssignmentController_removeSeatAssignment18_invoker.call(UserSeatAssignmentController_2.removeSeatAssignment(userId))
      }
  
    // @LINE:49
    case controllers_UserSeatAssignmentController_getSeatAssignment19_route(params) =>
      call(params.fromPath[Long]("userId", None)) { (userId) =>
        controllers_UserSeatAssignmentController_getSeatAssignment19_invoker.call(UserSeatAssignmentController_2.getSeatAssignment(userId))
      }
  
    // @LINE:50
    case controllers_UserSeatAssignmentController_checkSeatAssignment20_route(params) =>
      call(params.fromPath[Long]("userId", None)) { (userId) =>
        controllers_UserSeatAssignmentController_checkSeatAssignment20_invoker.call(UserSeatAssignmentController_2.checkSeatAssignment(userId))
      }
  }
}
