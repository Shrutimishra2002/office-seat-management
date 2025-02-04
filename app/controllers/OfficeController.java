package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.smartcoin.utils.JsonUtil;
import models.Office;
import models.Seat;
import play.libs.Json;
import play.mvc.*;
import services.OfficeService;
import services.SeatService;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class OfficeController extends Controller {

    private final OfficeService officeService;
    private final SeatService seatService;

    @Inject
    public OfficeController(OfficeService officeService, SeatService seatService) {
        this.officeService = officeService;
        this.seatService = seatService;
    }

    // Create a new office
    public Result createOffice() {
        try {
            JsonNode json = request().body().asJson();
            if (json == null || !json.has("name") || !json.has("rows") || !json.has("columns")) {
                return badRequest("Missing office name, rows, or columns.");
            }

            String officeName = json.get("name").asText();
            int rows = json.get("rows").asInt();
            int columns = json.get("columns").asInt();

            // Validate rows and columns
            if (rows <= 0 || columns <= 0) {
                return badRequest("Rows and columns must be greater than 0.");
            }
            // first try to get office by name

            Office office = officeService.getFinder().where().eq("name", officeName).findUnique();
            if (office != null) {
                return badRequest("Office already exists.");
            }
            Office officeCreated = officeService.createOfficeByAdmin(officeName,columns,rows);

            createSeats(officeCreated);

            return ok(Json.newObject().put("message", "Office created successfully."));

        } catch (Exception e) {
            return internalServerError("Error creating office.");
        }
    }

    private void createSeats(Office office) {
        List<Seat> seats = new ArrayList<Seat>();

        for (int row = 1; row <= office.getRowsLength(); row++) {
            for (int col = 1; col <= office.getColumnsLength(); col++) {
                Seat seat = new Seat();
                seat.setOfficeID(office.getId());
                seat.setRowInNumber(row);
                seat.setColInNumber(col);
                seat.setStatus("available");

                seats.add(seat);
            }
        }

        seatService.batchSave(seats);
    }

    // Delete an office by ID
    public Result deleteOffice(Long id) {
        // Use Finder to find the office by its ID
        if(id == null) {
            return notFound("Office not found.");
        }

        Office office = officeService
                .findById(id);

        if (office != null) {
            // Delete the office if it exists
            office.delete();
            return ok(Json.newObject().put("message", "Office deleted successfully."));
        } else {
            return notFound(Json.newObject().put("error", "Office not found."));
        }
    }


    // Get list of all offices
    public Result getOffices() {
        List<Office> offices = officeService.getFinder().findList();

        if (offices == null || offices.isEmpty()) {
            return notFound(Json.newObject().put("error", "No offices found."));
        }

        // Convert office list to JSON
        ArrayNode officesArray = Json.newArray();
        for (Office office : offices) {
            ObjectNode officeJson = Json.newObject();
            officeJson.put("id", office.getId());
            officeJson.put("name", office.getName());
            officeJson.put("rows", office.getRowsLength());
            officeJson.put("columns", office.getColumnsLength());
            officesArray.add(officeJson);
        }

        return ok(officesArray);
    }

    public Result getOfficeById(Long id) {
        // Use the Finder to get the office by ID
        Office office = officeService.findById(id); // Using Ebean's Finder to fetch office by ID

        if (office != null) {
            return ok(JsonUtil.toJson(office));  // Return the office data as JSON
        } else {
            return notFound(Json.newObject().put("error", "Office not found."));  // Return error if not found
        }
    }
}
