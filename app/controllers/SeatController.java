package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.smartcoin.utils.JsonUtil;
import models.Seat;
import play.libs.Json;
import play.mvc.*;
import services.SeatService;

import java.util.List;

import static models.Seat.OFFICE_ID;

public class SeatController extends Controller {

    private final SeatService seatService;

    @Inject
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    public Result addSeat(Long officeId) {
        JsonNode json = request().body().asJson();

        if (json == null) {
            return badRequest("Invalid JSON data");
        }
        try {
            String status = json.get("status").asText();
            Seat seat = seatService.createSeat(status, officeId);

            ObjectNode response = Json.newObject();
            response.put("message", "Seat added successfully");
            response.set("seat", createSeatJson(seat));

            return ok(response);
        } catch (Exception e) {
            return internalServerError("Error occurred while adding seat: " + e.getMessage());
        }
    }

    public Result updateSeat(Long id) {
        JsonNode json = request().body().asJson();

        if (json == null) {
            return badRequest("Invalid JSON data");
        }

        try {
            String status = json.has("status") ? json.get("status").asText() : null;
            if (status == null) {
                return badRequest("Status is Required");
            }
            Seat seat = seatService.updateSeat(id, status);

            if (seat == null) {
                return notFound("Seat not found");
            }
            ObjectNode response = Json.newObject();
            response.put("message", "Seat updated successfully");
            response.set("seat", createSeatJson(seat));

            return ok(response);

        } catch (Exception e) {
            return internalServerError("Error occurred while updating seat: " + e.getMessage());
        }
    }

    private ObjectNode createSeatJson(Seat seat) {
        ObjectNode seatJson = Json.newObject();
        seatJson.put("status", seat.getStatus());
        seatJson.put("RowLength", seat.getRowInNumber().toString());
        seatJson.put("ColLength", seat.getColInNumber().toString());
        seatJson.put("createdAt", seat.getCreatedAt().toString());
        seatJson.put("updatedAt", seat.getUpdatedAt().toString());
        return seatJson;
    }

    public Result deleteSeat(Long id) {
        try {
            boolean isDeleted = seatService.deleteSeat(id);

            if (!isDeleted) {
                return notFound("Seat not found");
            }

            ObjectNode response = Json.newObject();
            response.put("message", "Seat deleted successfully");

            return ok(response);
        } catch (Exception e) {
            return internalServerError("Error occurred while deleting seat: " + e.getMessage());
        }
    }

    public Result getSeats(Long officeId) {
        try {
            return ok(Json.toJson(seatService.findByField(OFFICE_ID, officeId)));

        } catch (Exception e) {
            return internalServerError("Error occurred while fetching seats: " + e.getMessage());
        }
    }


    public Result requestSeat(Long seatId) {
        try {
            Seat seat = seatService.requestSeat(seatId);

            if (seat == null) {
                return notFound("Seat not found");
            }

            if (!seat.getStatus().equals("Available")) {
                return badRequest("Seat is not available for booking");
            }

            ObjectNode response = Json.newObject();
            response.put("message", "Booking request sent to admin successfully");
            response.set("seat", createSeatJson(seat));

            return ok(response);
        } catch (Exception e) {
            return internalServerError("Error occurred while requesting seat: " + e.getMessage());
        }
    }

    public Result allSeatsFromaSingleOffice(Long officeId) {
        try {
            List<Seat> seats = seatService.allSeatsFromaSingleOffice(officeId);
            return ok(JsonUtil.toJson(seats));
        } catch (Exception e) {
            return notFound("Error while fetching seats from office: " + officeId);
        }
    }


}
