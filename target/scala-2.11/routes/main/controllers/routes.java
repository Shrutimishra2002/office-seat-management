
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shruti_sm1540_sc418/Documents/OfficeSeatManagement/conf/routes
// @DATE:Tue Feb 04 11:37:14 IST 2025

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAdminController AdminController = new controllers.ReverseAdminController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseOfficeController OfficeController = new controllers.ReverseOfficeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseSeatController SeatController = new controllers.ReverseSeatController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserController UserController = new controllers.ReverseUserController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserSeatAssignmentController UserSeatAssignmentController = new controllers.ReverseUserSeatAssignmentController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAdminController AdminController = new controllers.javascript.ReverseAdminController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseOfficeController OfficeController = new controllers.javascript.ReverseOfficeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseSeatController SeatController = new controllers.javascript.ReverseSeatController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserController UserController = new controllers.javascript.ReverseUserController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserSeatAssignmentController UserSeatAssignmentController = new controllers.javascript.ReverseUserSeatAssignmentController(RoutesPrefix.byNamePrefix());
  }

}
