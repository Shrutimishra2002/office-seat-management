
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shruti_sm1540_sc418/Documents/OfficeSeatManagement/conf/routes
// @DATE:Tue Feb 04 11:37:14 IST 2025

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:19
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:33
  class ReverseAdminController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def getSeatUtilizationReport: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AdminController.getSeatUtilizationReport",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/seat-utilization"})
        }
      """
    )
  
    // @LINE:35
    def removeSeat: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AdminController.removeSeat",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/seats/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:34
    def addSeat: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AdminController.addSeat",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/seats"})
        }
      """
    )
  
  }

  // @LINE:39
  class ReverseOfficeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:39
    def createOffice: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.OfficeController.createOffice",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "office/create"})
        }
      """
    )
  
    // @LINE:40
    def deleteOffice: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.OfficeController.deleteOffice",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "office/delete" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:42
    def getOffices: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.OfficeController.getOffices",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "offices/list"})
        }
      """
    )
  
    // @LINE:43
    def getOfficeById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.OfficeController.getOfficeById",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "office/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
  }

  // @LINE:26
  class ReverseSeatController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:28
    def deleteSeat: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SeatController.deleteSeat",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "seats/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:27
    def updateSeat: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SeatController.updateSeat",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "updateseats/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:26
    def addSeat: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SeatController.addSeat",
      """
        function(officeId0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "office/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("officeId", officeId0) + "/seats/add"})
        }
      """
    )
  
    // @LINE:30
    def allSeatsFromaSingleOffice: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SeatController.allSeatsFromaSingleOffice",
      """
        function(officeId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "seatsfromoffice/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("officeId", officeId0)})
        }
      """
    )
  
    // @LINE:29
    def getSeats: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SeatController.getSeats",
      """
        function(officeId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "office/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("officeId", officeId0) + "/seats"})
        }
      """
    )
  
  }

  // @LINE:19
  class ReverseUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def signUp: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.signUp",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/signup"})
        }
      """
    )
  
    // @LINE:21
    def home: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.home",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "home"})
        }
      """
    )
  
    // @LINE:23
    def listUsersNotAdmin: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.listUsersNotAdmin",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "listUsersNotAdmin"})
        }
      """
    )
  
    // @LINE:22
    def testConnection: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.testConnection",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:20
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/login"})
        }
      """
    )
  
  }

  // @LINE:47
  class ReverseUserSeatAssignmentController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:48
    def removeSeatAssignment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserSeatAssignmentController.removeSeatAssignment",
      """
        function(userId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "removeSeatAssignment/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId0)})
        }
      """
    )
  
    // @LINE:47
    def assignSeatToUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserSeatAssignmentController.assignSeatToUser",
      """
        function(userId0,seatId1) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "assignSeatToUser/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId0) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("seatId", seatId1)})
        }
      """
    )
  
    // @LINE:49
    def getSeatAssignment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserSeatAssignmentController.getSeatAssignment",
      """
        function(userId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAssignedSeat/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId0)})
        }
      """
    )
  
    // @LINE:50
    def checkSeatAssignment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserSeatAssignmentController.checkSeatAssignment",
      """
        function(userId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "checkUserExists/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId0)})
        }
      """
    )
  
  }


}
