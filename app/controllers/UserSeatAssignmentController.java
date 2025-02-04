package controllers;

import com.smartcoin.utils.JsonUtil;
import models.Seat;
import models.UserSeatAssignment;
import play.mvc.Controller;
import play.mvc.Result;
import services.SeatService;
import services.UserSeatAssignmentService;

import javax.inject.Inject;

public class UserSeatAssignmentController extends Controller {
    private final SeatService seatService;
    private final UserSeatAssignmentService userSeatAssignmentService;

    @Inject
    public UserSeatAssignmentController(UserSeatAssignmentService userSeatAssignmentService, SeatService seatService) {
        this.userSeatAssignmentService = userSeatAssignmentService;
        this.seatService = seatService;
    }

    public Result assignSeatToUser(Long userId, Long seatId) {
        try {
            UserSeatAssignment userSeatAssignment = userSeatAssignmentService.assignSeat(userId, seatId);
            seatService.updateSeat(seatId, "reserved");
            if (userSeatAssignment == null) {
                return badRequest("User already has a seat assigned.");
            }
            return ok("Seat assigned successfully.");
        } catch (Exception e) {
            return internalServerError("Error assigning seat: " + e.getMessage());
        }
    }

    public Result checkSeatAssignment(Long userId) {
        try {
            boolean hasAssignedSeat = userSeatAssignmentService.hasAssignedSeat(userId);
            Long seatId=userSeatAssignmentService.getAssignedSeat(userId);
            if (hasAssignedSeat && (seatId != null)) {
                return ok("You have already been assigned a seat." + seatId );
            } else {
                return ok("No seat assigned yet.");
            }
        } catch (Exception e) {
            return internalServerError("Error checking seat assignment: " + e.getMessage());
        }
    }


    public Result removeSeatAssignment(Long userId) {
        try {
            userSeatAssignmentService.removeAssignment(userId);
            return ok("User has been removed from the seat assignment: "  );
        } catch (Exception e) {
            return internalServerError("Error removing seat assignment: " + e.getMessage());
        }
    }

    public Result getSeatAssignment(Long userId) {
        try {
            Long seatID = userSeatAssignmentService.getAssignedSeat(userId);
            Seat seat = seatService.getFinder().byId(seatID);
            return ok(JsonUtil.toJson(seat));
        } catch (Exception e) {
            return internalServerError("Error getting seat assignment: " + e.getMessage());
        }
    }
}
