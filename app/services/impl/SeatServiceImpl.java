package services.impl;

import com.smartcoin.db.services.ModelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import models.Seat;
import services.SeatService;

import java.util.List;
import java.util.Date;

@Slf4j
public class SeatServiceImpl extends ModelServiceImpl<Long,Seat> implements SeatService {

    @Override
    public Seat createSeat(String location, Long officeId) {
        Seat seat = new Seat();
//        seat.setStatus(status);
        seat.setOfficeID(officeId); // Associate seat with the office
        seat.setCreatedAt(new Date());
        seat.setUpdatedAt(new Date());
        seat.save(); // Save the seat to the database
        return seat;
    }


    @Override
    public Seat updateSeat(Long id,String status) {
        Seat seat = getFinder().byId(id);

        if (seat == null) {
            return null;
        }
        seat.setStatus(status);
        seat.update();

        return seat;
    }

    @Override
    public boolean deleteSeat(Long id){
        Seat seat = getFinder().byId(id);
        if (seat != null) {
            seat.delete();
            return true;
        }
        return false;
    }

    @Override
    public List<Seat> getSeats(Long officeId) {
//        log.debug("getSeats officeId={}", officeId);
        return getFinder().where().eq("office_id", officeId).findList();
    }

    @Override
    public Seat requestSeat(Long id){
        Seat seat = getFinder().byId(id);
        if (seat !=null && seat.getStatus().equals("Available")) {
            seat.setStatus("Requested");
            seat.update();
            return seat;
        }
        return null;

    }

    @Override
    public List<Seat> allSeatsFromaSingleOffice(Long officeId) {
        return getFinder().where().eq("office_id", officeId).findList();
    }


    @Override
    public void assignSeat(Long seatId, Long officeId) {
        getFinder().where().eq("office_id",officeId).eq("seat_id",seatId);
    }

}
