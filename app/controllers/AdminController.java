package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Seat;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

public class AdminController extends Controller {

    public Result getSeatUtilizationReport() {
        try {
            List<Seat> seats = Ebean.find(Seat.class).findList();

            ObjectNode response = Json.newObject();
            response.put("message", "Seat utilization report fetched successfully");
            response.put("seats", Json.toJson(seats));

            return ok(response);

        } catch (Exception e) {
            return internalServerError("Error fetching seat utilization report: " + e.getMessage());
        }
    }

    public Result addSeat() {
        JsonNode json = request().body().asJson();

        if (json == null) {
            return badRequest("Invalid JSON data");
        }

        try {
            String status = json.get("status").asText();
            Seat seat = new Seat();
            seat.setStatus(status);
            seat.save();

            ObjectNode response = Json.newObject();
            response.put("message", "Seat added successfully");
            response.set("seat", Json.toJson(seat));

            return ok(response);

        } catch (Exception e) {
            return internalServerError("Error adding seat: " + e.getMessage());
        }
    }

    public Result removeSeat(Long id) {
        try {
            Seat seat = Ebean.find(Seat.class, id);

            if (seat == null) {
                return notFound("Seat not found");
            }

            seat.delete();

            ObjectNode response = Json.newObject();
            response.put("message", "Seat removed successfully");

            return ok(response);

        } catch (Exception e) {
            return internalServerError("Error removing seat: " + e.getMessage());
        }
    }

    public Result approveBookingRequest(Long seatId, boolean isApproved) {
        try {
            Seat seat = Ebean.find(Seat.class, seatId);

            if (seat == null) {
                return notFound("Seat not found");
            }

            if (isApproved) {
                seat.setStatus("Booked");
                seat.update();
            } else {

                seat.setStatus("Available");
                seat.update();
            }

            ObjectNode response = Json.newObject();
            response.put("message", isApproved ? "Booking approved" : "Booking rejected");
            response.set("seat", createSeatJson(seat));

            return ok(response);
        } catch (Exception e) {
            return internalServerError("Error occurred while processing booking request: " + e.getMessage());
        }
    }


    private ObjectNode createSeatJson(Seat seat) {
        ObjectNode seatJson = Json.newObject();
        seatJson.put("status", seat.getStatus());
        seatJson.put("createdAt", seat.getCreatedAt().toString());
        seatJson.put("updatedAt", seat.getUpdatedAt().toString());
        return seatJson;
    }


}
