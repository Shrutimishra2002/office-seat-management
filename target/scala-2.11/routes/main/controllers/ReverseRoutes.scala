
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shruti_sm1540_sc418/Documents/OfficeSeatManagement/conf/routes
// @DATE:Tue Feb 04 11:37:14 IST 2025

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:19
package controllers {

  // @LINE:33
  class ReverseAdminController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def getSeatUtilizationReport(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "admin/seat-utilization")
    }
  
    // @LINE:35
    def removeSeat(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "admin/seats/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:34
    def addSeat(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "admin/seats")
    }
  
  }

  // @LINE:39
  class ReverseOfficeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:39
    def createOffice(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "office/create")
    }
  
    // @LINE:40
    def deleteOffice(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "office/delete" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:42
    def getOffices(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "offices/list")
    }
  
    // @LINE:43
    def getOfficeById(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "office/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
  }

  // @LINE:26
  class ReverseSeatController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:28
    def deleteSeat(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "seats/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:27
    def updateSeat(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("PUT", _prefix + { _defaultPrefix } + "updateseats/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:26
    def addSeat(officeId:Long): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "office/" + implicitly[PathBindable[Long]].unbind("officeId", officeId) + "/seats/add")
    }
  
    // @LINE:30
    def allSeatsFromaSingleOffice(officeId:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "seatsfromoffice/" + implicitly[PathBindable[Long]].unbind("officeId", officeId))
    }
  
    // @LINE:29
    def getSeats(officeId:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "office/" + implicitly[PathBindable[Long]].unbind("officeId", officeId) + "/seats")
    }
  
  }

  // @LINE:19
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def signUp(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "users/signup")
    }
  
    // @LINE:21
    def home(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "home")
    }
  
    // @LINE:23
    def listUsersNotAdmin(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "listUsersNotAdmin")
    }
  
    // @LINE:22
    def testConnection(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
    // @LINE:20
    def login(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "users/login")
    }
  
  }

  // @LINE:47
  class ReverseUserSeatAssignmentController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:48
    def removeSeatAssignment(userId:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "removeSeatAssignment/" + implicitly[PathBindable[Long]].unbind("userId", userId))
    }
  
    // @LINE:47
    def assignSeatToUser(userId:Long, seatId:Long): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "assignSeatToUser/" + implicitly[PathBindable[Long]].unbind("userId", userId) + "/" + implicitly[PathBindable[Long]].unbind("seatId", seatId))
    }
  
    // @LINE:49
    def getSeatAssignment(userId:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "getAssignedSeat/" + implicitly[PathBindable[Long]].unbind("userId", userId))
    }
  
    // @LINE:50
    def checkSeatAssignment(userId:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "checkUserExists/" + implicitly[PathBindable[Long]].unbind("userId", userId))
    }
  
  }


}
